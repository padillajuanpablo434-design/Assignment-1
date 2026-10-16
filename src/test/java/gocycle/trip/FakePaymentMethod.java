package gocycle.trip;

import gocycle.payment.PaymentMethod;
import gocycle.pricing.Money;

/**
 * Test double that records holds and charges and can be told to decline them.
 */
class FakePaymentMethod implements PaymentMethod {

    boolean approveHold = true;
    boolean approveCharge = true;

    Money heldAmount;
    boolean holdReleased;
    Money chargedAmount;

    @Override
    public boolean placeHold(Money amount) {
        if (!approveHold) {
            return false;
        }
        heldAmount = amount;
        return true;
    }

    @Override
    public void releaseHold() {
        holdReleased = true;
    }

    @Override
    public boolean charge(Money amount) {
        if (!approveCharge) {
            return false;
        }
        chargedAmount = amount;
        return true;
    }

    @Override
    public String displayName() {
        return "Fake card";
    }
}
