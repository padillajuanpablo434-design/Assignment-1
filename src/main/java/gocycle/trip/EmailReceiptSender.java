package gocycle.trip;

import java.io.PrintStream;
import java.util.Objects;

/**
 * Emails receipts to the rider. Simulated by printing to the console.
 */
public class EmailReceiptSender implements ReceiptSender {

    private final PrintStream out;

    /**
     * Prints receipts to standard output.
     */
    public EmailReceiptSender() {
        this(System.out);
    }

    /**
     * @param out where to print receipts
     */
    public EmailReceiptSender(PrintStream out) {
        this.out = Objects.requireNonNull(out, "out");
    }

    @Override
    public void send(Receipt receipt) {
        out.println("[Email] To: " + receipt.getRider().getEmail());
        out.println("Subject: Your GoCycle receipt for trip " + receipt.getTripId());
        out.println(receipt);
    }
}
