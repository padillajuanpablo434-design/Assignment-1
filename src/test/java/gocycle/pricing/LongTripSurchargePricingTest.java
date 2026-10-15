package gocycle.pricing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LongTripSurchargePricingTest {

    // $1.00 unlock + $0.10/min keeps the expected totals easy to check by hand.
    private final PricingStrategy base = new PricingStrategy() {
        @Override
        public Money unlockFee() {
            return Money.of(1, 0);
        }

        @Override
        public Money perMinuteRate() {
            return Money.ofCents(10);
        }
    };

    private final PricingStrategy pricing = new LongTripSurchargePricing(base);

    @Test
    void noSurchargeAtExactlyTwoHours() {
        assertEquals(Money.of(13, 0), pricing.calculate(120));
    }

    @Test
    void surchargeAddedJustOverTwoHours() {
        assertEquals(Money.of(18, 10), pricing.calculate(121));
    }

    @Test
    void shortTripCostsSameAsBase() {
        assertEquals(base.calculate(10), pricing.calculate(10));
    }

    @Test
    void ratesComeFromWrappedStrategy() {
        assertEquals(base.unlockFee(), pricing.unlockFee());
        assertEquals(base.perMinuteRate(), pricing.perMinuteRate());
    }
}
