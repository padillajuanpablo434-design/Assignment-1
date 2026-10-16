package gocycle.trip;

import gocycle.payment.PaymentMethod;
import gocycle.pricing.Money;
import gocycle.pricing.PricingStrategy;
import gocycle.rider.Rider;
import gocycle.station.DockingPoint;
import gocycle.vehicle.Vehicle;
import gocycle.vehicle.VehicleStatus;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

/**
 * One rental of one vehicle by one rider, from unlock to confirmed lock.
 * <p>
 * A trip moves {@code NOT_STARTED -> ACTIVE -> COMPLETED}. Starting places a deposit hold;
 * ending charges the rider through their {@link PricingStrategy}, releases the hold and
 * returns a {@link Receipt}.
 */
public class Trip {

    /** Refundable hold placed on the rider's payment method for the length of the trip: $50.00. */
    public static final Money DEPOSIT_HOLD = Money.of(50, 0);

    private final String id;
    private final Rider rider;
    private final Vehicle vehicle;
    private final PaymentMethod payment;
    private TripState state = TripState.NOT_STARTED;
    private Instant startTime;
    private Instant endTime;

    /**
     * Creates a trip that has not started yet.
     *
     * @param id      the unique trip id
     * @param rider   who is riding
     * @param vehicle the vehicle to be ridden
     * @param payment how the rider will pay
     */
    public Trip(String id, Rider rider, Vehicle vehicle, PaymentMethod payment) {
        this.id = Objects.requireNonNull(id, "id");
        this.rider = Objects.requireNonNull(rider, "rider");
        this.vehicle = Objects.requireNonNull(vehicle, "vehicle");
        this.payment = Objects.requireNonNull(payment, "payment");
    }

    /**
     * Starts the trip: checks the vehicle can be rented, places the deposit hold and
     * marks the vehicle in use.
     *
     * @param now when the vehicle is unlocked
     * @throws IllegalStateException       if the trip has already started
     * @throws VehicleNotRentableException if the vehicle cannot be rented
     * @throws PaymentDeclinedException    if the deposit hold is refused
     */
    public void start(Instant now) {
        Objects.requireNonNull(now, "now");
        requireState(TripState.NOT_STARTED, "start");
        if (!vehicle.isRentable()) {
            throw new VehicleNotRentableException(vehicle.getId());
        }
        if (!payment.placeHold(DEPOSIT_HOLD)) {
            throw new PaymentDeclinedException(
                    "Deposit hold of " + DEPOSIT_HOLD + " declined by " + payment.displayName());
        }
        vehicle.setStatus(VehicleStatus.IN_USE);
        startTime = now;
        state = TripState.ACTIVE;
    }

    /**
     * Ends the trip once the vehicle is confirmed locked at {@code point}: charges the rider,
     * releases the deposit hold and makes the vehicle available again.
     * <p>
     * If the vehicle is not locked, nothing changes and the trip keeps running.
     *
     * @param point where the vehicle was returned
     * @param now   when the vehicle was locked
     * @return the receipt for the trip
     * @throws IllegalStateException      if the trip is not active
     * @throws IllegalArgumentException   if {@code now} is before the start time
     * @throws VehicleNotLockedException  if this vehicle is not locked at {@code point}
     * @throws PaymentDeclinedException   if the charge is refused
     */
    public Receipt end(DockingPoint point, Instant now) {
        Objects.requireNonNull(point, "point");
        Objects.requireNonNull(now, "now");
        requireState(TripState.ACTIVE, "end");
        if (now.isBefore(startTime)) {
            throw new IllegalArgumentException("End time " + now + " is before start time " + startTime);
        }
        if (point.getDocked() != vehicle || !point.isLocked()) {
            throw new VehicleNotLockedException(vehicle.getId(), point.getId());
        }

        long minutes = billableMinutes(startTime, now);
        PricingStrategy pricing = rider.getPricing();
        Money total = pricing.calculate(minutes);
        if (!payment.charge(total)) {
            throw new PaymentDeclinedException(
                    "Charge of " + total + " declined by " + payment.displayName());
        }
        payment.releaseHold();
        vehicle.setStatus(VehicleStatus.AVAILABLE);
        endTime = now;
        state = TripState.COMPLETED;

        Money unlockFee = pricing.unlockFee();
        Money timeCharge = pricing.perMinuteRate().multiply(minutes);
        Money surcharge = total.subtract(unlockFee).subtract(timeCharge);
        return new Receipt(id, rider, vehicle.getId(), startTime, endTime,
                minutes, unlockFee, timeCharge, surcharge);
    }

    /**
     * Rounds a duration up to whole minutes, so any part of a minute is billed as a full one.
     */
    private static long billableMinutes(Instant start, Instant end) {
        Duration duration = Duration.between(start, end);
        long minutes = duration.toMinutes();
        if (!duration.minusMinutes(minutes).isZero()) {
            minutes++;
        }
        return minutes;
    }

    private void requireState(TripState expected, String action) {
        if (state != expected) {
            throw new IllegalStateException("Cannot " + action + " trip " + id + " while " + state);
        }
    }

    public String getId() {
        return id;
    }

    public Rider getRider() {
        return rider;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public PaymentMethod getPayment() {
        return payment;
    }

    public TripState getState() {
        return state;
    }

    /**
     * @return when the vehicle was unlocked, or {@code null} before the trip starts
     */
    public Instant getStartTime() {
        return startTime;
    }

    /**
     * @return when the vehicle was confirmed locked, or {@code null} until the trip completes
     */
    public Instant getEndTime() {
        return endTime;
    }
}
