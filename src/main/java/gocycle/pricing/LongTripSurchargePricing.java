package gocycle.pricing;

import java.util.Objects;

/**
 * Adds a flat surcharge to trips longer than 2 hours (Decorator).
 * <p>
 * Wraps any {@link PricingStrategy}, so the surcharge rule lives in one place
 * instead of being repeated in every rider type's pricing.
 */
public class LongTripSurchargePricing implements PricingStrategy {

    /** Trips longer than this many minutes are surcharged. */
    public static final long LONG_TRIP_THRESHOLD_MINUTES = 120;

    /** Added to the cost of a long trip: $5.00. */
    public static final Money LONG_TRIP_SURCHARGE = Money.of(5, 0);

    private final PricingStrategy base;

    /**
     * @param base the pricing to add the surcharge to
     */
    public LongTripSurchargePricing(PricingStrategy base) {
        this.base = Objects.requireNonNull(base, "base");
    }

    @Override
    public Money unlockFee() {
        return base.unlockFee();
    }

    @Override
    public Money perMinuteRate() {
        return base.perMinuteRate();
    }

    /**
     * @return the base cost, plus {@link #LONG_TRIP_SURCHARGE} if {@code minutes} is over
     *         {@link #LONG_TRIP_THRESHOLD_MINUTES}
     */
    @Override
    public Money calculate(long minutes) {
        Money cost = base.calculate(minutes);
        if (minutes > LONG_TRIP_THRESHOLD_MINUTES) {
            cost = cost.add(LONG_TRIP_SURCHARGE);
        }
        return cost;
    }
}
