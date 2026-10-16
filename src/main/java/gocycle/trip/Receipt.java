package gocycle.trip;

import gocycle.pricing.Money;
import gocycle.rider.Rider;
import java.time.Instant;
import java.util.Objects;

/**
 * An immutable record of a completed, paid trip.
 * <p>
 * The total is always the unlock fee plus the time charge plus the surcharge.
 */
public final class Receipt {

    private final String tripId;
    private final Rider rider;
    private final String vehicleId;
    private final Instant startTime;
    private final Instant endTime;
    private final long minutes;
    private final Money unlockFee;
    private final Money timeCharge;
    private final Money surcharge;
    private final Money total;

    /**
     * @param tripId     the trip this receipt is for
     * @param rider      who rode and paid
     * @param vehicleId  the vehicle ridden
     * @param startTime  when the vehicle was unlocked
     * @param endTime    when the vehicle was confirmed locked
     * @param minutes    the billed minutes
     * @param unlockFee  the unlock fee charged
     * @param timeCharge the per-minute charge for the whole trip
     * @param surcharge  any extra charge, or {@link Money#ZERO}
     */
    public Receipt(String tripId, Rider rider, String vehicleId, Instant startTime, Instant endTime,
                   long minutes, Money unlockFee, Money timeCharge, Money surcharge) {
        this.tripId = Objects.requireNonNull(tripId, "tripId");
        this.rider = Objects.requireNonNull(rider, "rider");
        this.vehicleId = Objects.requireNonNull(vehicleId, "vehicleId");
        this.startTime = Objects.requireNonNull(startTime, "startTime");
        this.endTime = Objects.requireNonNull(endTime, "endTime");
        this.minutes = minutes;
        this.unlockFee = Objects.requireNonNull(unlockFee, "unlockFee");
        this.timeCharge = Objects.requireNonNull(timeCharge, "timeCharge");
        this.surcharge = Objects.requireNonNull(surcharge, "surcharge");
        this.total = unlockFee.add(timeCharge).add(surcharge);
    }

    public String getTripId() {
        return tripId;
    }

    public Rider getRider() {
        return rider;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public long getMinutes() {
        return minutes;
    }

    public Money getUnlockFee() {
        return unlockFee;
    }

    public Money getTimeCharge() {
        return timeCharge;
    }

    public Money getSurcharge() {
        return surcharge;
    }

    /**
     * @return {@code true} if a surcharge was applied
     */
    public boolean hasSurcharge() {
        return !surcharge.equals(Money.ZERO);
    }

    public Money getTotal() {
        return total;
    }

    /**
     * Formats the receipt as text for display or sending.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GoCycle receipt for trip ").append(tripId).append('\n');
        sb.append("Rider:       ").append(rider.getName()).append(" (").append(rider.getId()).append(")\n");
        sb.append("Vehicle:     ").append(vehicleId).append('\n');
        sb.append("Start:       ").append(startTime).append('\n');
        sb.append("End:         ").append(endTime).append('\n');
        sb.append("Minutes:     ").append(minutes).append('\n');
        sb.append("Unlock fee:  ").append(unlockFee).append('\n');
        sb.append("Time charge: ").append(timeCharge).append('\n');
        if (hasSurcharge()) {
            sb.append("Surcharge:   ").append(surcharge).append('\n');
        }
        sb.append("Total:       ").append(total);
        return sb.toString();
    }
}
