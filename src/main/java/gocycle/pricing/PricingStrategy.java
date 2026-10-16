package gocycle.pricing;

/**
 * How a rider type is charged for a trip (Strategy).
 * <p>
 * Each rider type (casual, member, student, and any added later) supplies its own
 * strategy, so new pricing can be added without changing {@code Rider} or {@code Trip}.
 * Extra rules such as the long-trip surcharge are added by wrapping a strategy in a
 * decorator, e.g. {@link LongTripSurchargePricing}.
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
     * Calculates the total cost of a trip.
     * <p>
     * The default is the unlock fee plus the per-minute rate for each minute.
     * Decorators override this to add extra charges.
     *
     * @param minutes the trip length in whole minutes
     * @return the total cost
     */
    default Money calculate(long minutes) {
        return unlockFee().add(perMinuteRate().multiply(minutes));
    }
}
