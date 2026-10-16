package gocycle.pricing;

/**
 * How a rider type is charged for a trip (Strategy).
 * <p>
 * Each rider type (casual, member, student, and any added later) supplies its own
 * strategy, so new pricing can be added without changing {@code Rider} or {@code Trip}.
 */
public interface PricingStrategy {

    /**
     * @return the flat fee charged when a vehicle is unlocked
     */
    Money unlockFee();

    /**
     * @return the charge for each minute of riding
     */
    Money perMinuteRate();

    /**
     * Calculates the total cost of a trip: unlock fee plus per-minute charge,
     * plus a long-trip surcharge for trips over 2 hours.
     *
     * @param minutes the trip length in whole minutes
     * @return the total cost
     */
    Money calculate(long minutes);
}
