package gocycle.trip;

import java.io.PrintStream;
import java.util.Objects;

/**
 * Sends receipts to the rider's phone app. Simulated by printing to the console.
 */
public class AppReceiptSender implements ReceiptSender {

    private final PrintStream out;

    /**
     * Prints receipts to standard output.
     */
    public AppReceiptSender() {
        this(System.out);
    }

    /**
     * @param out where to print receipts
     */
    public AppReceiptSender(PrintStream out) {
        this.out = Objects.requireNonNull(out, "out");
    }

    @Override
    public void send(Receipt receipt) {
        out.println("[App] Receipt pushed to rider " + receipt.getRider().getId());
        out.println(receipt);
    }
}
