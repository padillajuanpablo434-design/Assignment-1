package gocycle.rider;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gocycle.pricing.Money;
import org.junit.jupiter.api.Test;

class MemberRiderTest {

    private final Rider rider = new MemberRider("R2", "Morgan", "morgan@example.com");

    @Test
    void tenMinuteTripCostsTimeChargeOnly() {
        // $0.00 + 10 x $0.20
        assertEquals(Money.of(2, 0), rider.getPricing().calculate(10));
    }

    @Test
    void longTripIncludesSurcharge() {
        // $0.00 + 121 x $0.20 + $5.00
        assertEquals(Money.of(29, 20), rider.getPricing().calculate(121));
    }
}
