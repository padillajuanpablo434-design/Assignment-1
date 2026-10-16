package gocycle.trip;

/**
 * Thrown when the rider's payment method refuses the deposit hold or the trip charge.
 * The trip's state is left unchanged so the step can be retried.
 */
public class PaymentDeclinedException extends RuntimeException {

    /**
     * @param message what was declined
     */
    public PaymentDeclinedException(String message) {
        super(message);
    }
}
