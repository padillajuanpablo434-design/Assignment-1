package gocycle.payment;

/** Demonstration adapter for a city transit card. */
public final class TransitCardPayment extends AbstractInMemoryPayment {
    public TransitCardPayment(String maskedLabel) { super(maskedLabel); }
}
