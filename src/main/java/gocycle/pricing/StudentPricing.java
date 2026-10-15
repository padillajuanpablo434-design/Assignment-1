package gocycle.pricing;

/**
 * Reduced pricing for students.
 */
public class StudentPricing implements PricingStrategy {

    /** Charged once when the vehicle is unlocked: $1.00. */
    public static final Money UNLOCK_FEE = Money.of(1, 0);

    /** Charged for each minute of riding: $0.25. */
    public static final Money PER_MINUTE_RATE = Money.ofCents(25);

    @Override
    public Money unlockFee() {
        return UNLOCK_FEE;
    }

    @Override
    public Money perMinuteRate() {
        return PER_MINUTE_RATE;
    }
}
