package gocycle.pricing;

/**
 * Pricing for monthly members: no unlock fee and a lower per-minute rate.
 */
public class MemberPricing implements PricingStrategy {

    /** Members pay no unlock fee. */
    public static final Money UNLOCK_FEE = Money.ZERO;

    /** Charged for each minute of riding: $0.20. */
    public static final Money PER_MINUTE_RATE = Money.ofCents(20);

    @Override
    public Money unlockFee() {
        return UNLOCK_FEE;
    }

    @Override
    public Money perMinuteRate() {
        return PER_MINUTE_RATE;
    }
}
