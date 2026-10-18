package gocycle.payment;

import gocycle.pricing.Money;
import java.util.Objects;

/**
 * Decorates a payment method so Trip cannot place a hold or charge it
 * when the current zone does not accept that payment type.
 */
public final class ZoneRestrictedPaymentMethod implements PaymentMethod {
    private final Zone zone;
    private final PaymentMethod delegate;

    public ZoneRestrictedPaymentMethod(Zone zone, PaymentMethod delegate) {
        this.zone = Objects.requireNonNull(zone, "zone");
        this.delegate = Objects.requireNonNull(delegate, "delegate");
    }

    @Override
    public boolean placeHold(Money amount) {
        return zone.allows(delegate) && delegate.placeHold(amount);
    }

    @Override
    public void releaseHold() {
        delegate.releaseHold();
    }

    @Override
    public boolean charge(Money amount) {
        return zone.allows(delegate) && delegate.charge(amount);
    }

    @Override
    public String displayName() { return delegate.displayName(); }
}
