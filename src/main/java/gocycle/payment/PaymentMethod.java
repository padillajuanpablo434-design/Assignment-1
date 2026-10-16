package gocycle.payment;

import gocycle.pricing.Money;

/**
 * A way for a rider to pay: credit card, city transit card or phone wallet.
 * <p>
 * Stub for Person C, who owns payments.
 */
public interface PaymentMethod {

    /**
     * Places a refundable deposit hold at the start of a trip.
     *
     * @param amount the deposit to hold
     * @return {@code true} if the hold was accepted
     */
    boolean placeHold(Money amount);

    /**
     * Releases the deposit hold at the end of a trip.
     */
    void releaseHold();

    /**
     * Charges the final trip cost.
     *
     * @param amount the amount to charge
     * @return {@code true} if the charge succeeded
     */
    boolean charge(Money amount);

    /**
     * @return a name to show on screens and receipts, e.g. "Visa ending 4242"
     */
    String displayName();
}
