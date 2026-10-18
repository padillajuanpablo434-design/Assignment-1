package gocycle.payment;

import gocycle.pricing.Money;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentIntegrationTest {
    @Test void allowedPaymentHoldsChargesAndReleases() {
        Zone zone = new Zone("Z1", "Central", Set.of(CreditCardPayment.class));
        CreditCardPayment card = new CreditCardPayment("Card ending 4242");
        PaymentMethod payment = new ZoneRestrictedPaymentMethod(zone, card);
        assertTrue(payment.placeHold(Money.of(50, 0)));
        assertTrue(card.hasActiveHold());
        assertFalse(payment.placeHold(Money.of(50, 0)), "cannot place two holds");
        assertTrue(payment.charge(Money.of(8, 25)));
        assertEquals(Money.of(8, 25), card.getTotalCharged());
        payment.releaseHold();
        assertFalse(card.hasActiveHold());
    }

    @Test void disallowedPaymentDoesNotPlaceHold() {
        Zone zone = new Zone("Z1", "Central", Set.of(CreditCardPayment.class));
        TransitCardPayment transit = new TransitCardPayment("Transit 0012");
        PaymentMethod payment = new ZoneRestrictedPaymentMethod(zone, transit);
        assertFalse(zone.allows(transit));
        assertFalse(payment.placeHold(Money.of(50, 0)));
        assertFalse(transit.hasActiveHold());
    }

    @Test void mobileWalletCanBeEnabledForAnotherZone() {
        Zone zone = new Zone("Z2", "Campus", Set.of(PhoneWalletPayment.class));
        assertTrue(zone.allows(new PhoneWalletPayment("Mobile wallet")));
        assertFalse(zone.allows(new CreditCardPayment("Card 4242")));
    }
}
