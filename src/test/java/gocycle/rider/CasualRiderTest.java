package gocycle.rider;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gocycle.pricing.Money;
import org.junit.jupiter.api.Test;

class CasualRiderTest {

    private final Rider rider = new CasualRider("R1", "Casey", "casey@example.com");

    @Test
    void tenMinuteTripCostsUnlockPlusTimeCharge() {
        // $1.50 + 10 x $0.35
        assertEquals(Money.of(5, 0), rider.getPricing().calculate(10));
    }

    @Test
    void longTripIncludesSurcharge() {
        // $1.50 + 121 x $0.35 + $5.00
        assertEquals(Money.of(48, 85), rider.getPricing().calculate(121));
    }
}
