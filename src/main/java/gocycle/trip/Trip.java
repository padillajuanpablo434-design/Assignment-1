package gocycle.trip;

import gocycle.payment.PaymentMethod;
import gocycle.pricing.Money;
import gocycle.rider.Rider;
import gocycle.vehicle.Vehicle;
import java.time.Instant;

/**
 * One rental of one vehicle by one rider, from unlock to confirmed lock.
 */
public class Trip {

    private final Rider rider;
    private final Vehicle vehicle;
    private final PaymentMethod payment;
    private final Instant startTime;
    private Instant endTime;

    /**
     * Starts a trip.
     *
     * @param rider     who is riding
     * @param vehicle   the vehicle being ridden
     * @param payment   how the rider will pay
     * @param startTime when the vehicle was unlocked
     */
    public Trip(Rider rider, Vehicle vehicle, PaymentMethod payment, Instant startTime) {
        this.rider = rider;
        this.vehicle = vehicle;
        this.payment = payment;
        this.startTime = startTime;
    }

    /**
     * @return who is riding
     */
    public Rider getRider() {
        return rider;
    }

    /**
     * @return the vehicle being ridden
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * @return how the rider will pay
     */
    public PaymentMethod getPayment() {
        return payment;
    }

    /**
     * @return when the vehicle was unlocked
     */
    public Instant getStartTime() {
        return startTime;
    }

    /**
     * @return when the vehicle was confirmed locked, or {@code null} while the trip is ongoing
     */
    public Instant getEndTime() {
        return endTime;
    }

    /**
     * @return {@code true} once the trip has ended
     */
    public boolean isEnded() {
        throw new UnsupportedOperationException("TODO: Person A");
    }

    /**
     * Ends the trip. Call this only once the vehicle is confirmed locked.
     *
     * @param endTime when the vehicle was confirmed locked
     */
    public void end(Instant endTime) {
        throw new UnsupportedOperationException("TODO: Person A");
    }

    /**
     * @return the trip length in whole minutes
     */
    public long getDurationMinutes() {
        throw new UnsupportedOperationException("TODO: Person A");
    }

    /**
     * Calculates the cost using the rider's pricing strategy.
     *
     * @return the total cost of the trip
     */
    public Money calculateCost() {
        throw new UnsupportedOperationException("TODO: Person A");
    }
}
