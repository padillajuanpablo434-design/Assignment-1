package gocycle.pricing;

/**
 * Pay-per-trip pricing for casual riders.
 */
public class CasualPricing implements PricingStrategy {

    /** Charged once when the vehicle is unlocked: $1.50. */
    public static final Money UNLOCK_FEE = Money.of(1, 50);

    /** Charged for each minute of riding: $0.35. */
    public static final Money PER_MINUTE_RATE = Money.ofCents(35);

    @Override
    public Money unlockFee() {
        return UNLOCK_FEE;
    }

    @Override
    public Money perMinuteRate() {
        return PER_MINUTE_RATE;
    }
}
