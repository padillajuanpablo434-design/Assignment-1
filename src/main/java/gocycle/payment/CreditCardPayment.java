package gocycle.payment;

/** Demonstration adapter for a credit card; use a masked display label only. */
public final class CreditCardPayment extends AbstractInMemoryPayment {
    public CreditCardPayment(String maskedLabel) { super(maskedLabel); }
}
