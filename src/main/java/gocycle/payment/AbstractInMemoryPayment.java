package gocycle.payment;

import gocycle.pricing.Money;
import java.util.Objects;

/**
 * Demonstration-only payment adapter. No real bank or card transaction occurs.
 * Tracks the deposit hold and successful simulated charges.
 */
public abstract class AbstractInMemoryPayment implements PaymentMethod {
    private final String label;
    private Money activeHold;
    private Money totalCharged = Money.ZERO;

    protected AbstractInMemoryPayment(String label) {
        this.label = Objects.requireNonNull(label, "label");
        if (label.isBlank()) throw new IllegalArgumentException("label cannot be blank");
    }

    @Override
    public final boolean placeHold(Money amount) {
        Objects.requireNonNull(amount, "amount");
        if (amount.compareTo(Money.ZERO) < 0 || activeHold != null) return false;
        activeHold = amount;
        return true;
    }

    @Override
    public final void releaseHold() {
        activeHold = null;
    }

    @Override
    public final boolean charge(Money amount) {
        Objects.requireNonNull(amount, "amount");
        if (amount.compareTo(Money.ZERO) < 0 || activeHold == null) return false;
        totalCharged = totalCharged.add(amount);
        return true;
    }

    @Override
    public final String displayName() {
        return label;
    }

    public final boolean hasActiveHold() {
        return activeHold != null;
    }

    public final Money getTotalCharged() {
        return totalCharged;
    }
}
