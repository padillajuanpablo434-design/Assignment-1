package gocycle.trip;

/**
 * Delivers a receipt to the rider after a trip is paid, e.g. through the app or by email.
 */
public interface ReceiptSender {

    /**
     * @param receipt the receipt to deliver
     */
    void send(Receipt receipt);
}
