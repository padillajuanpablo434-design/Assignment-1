package gocycle.rider;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gocycle.pricing.Money;
import org.junit.jupiter.api.Test;

class StudentRiderTest {

    private final Rider rider = new StudentRider("R3", "Sam", "sam@example.com");

    @Test
    void tenMinuteTripCostsUnlockPlusTimeCharge() {
        // $1.00 + 10 x $0.25
        assertEquals(Money.of(3, 50), rider.getPricing().calculate(10));
    }

    @Test
    void longTripIncludesSurcharge() {
        // $1.00 + 121 x $0.25 + $5.00
        assertEquals(Money.of(36, 25), rider.getPricing().calculate(121));
    }
}
