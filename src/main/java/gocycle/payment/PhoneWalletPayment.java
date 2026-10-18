package gocycle.payment;

/** Demonstration adapter for a mobile phone wallet. */
public final class PhoneWalletPayment extends AbstractInMemoryPayment {
    public PhoneWalletPayment(String maskedLabel) { super(maskedLabel); }
}
