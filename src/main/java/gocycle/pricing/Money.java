package gocycle.pricing;

import java.util.Objects;

/**
 * An immutable amount of money stored as a whole number of cents.
 * <p>
 * Integer cents avoid the rounding errors of floating-point arithmetic.
 * All arithmetic returns a new {@code Money}; instances are never modified.
 */
public final class Money implements Comparable<Money> {

    /** Zero dollars. */
    public static final Money ZERO = new Money(0);

    private final long cents;

    private Money(long cents) {
        this.cents = cents;
    }

    /**
     * Creates an amount from a number of cents.
     *
     * @param cents the amount in cents (may be negative, e.g. for refunds)
     * @return the amount
     */
    public static Money ofCents(long cents) {
        return new Money(cents);
    }

    /**
     * Creates an amount from whole dollars and cents, e.g. {@code of(2, 50)} is $2.50.
     *
     * @param dollars the whole-dollar part
     * @param cents   the cents part, from 0 to 99
     * @return the amount
     * @throws IllegalArgumentException if {@code cents} is outside 0 to 99
     */
    public static Money of(long dollars, int cents) {
        if (cents < 0 || cents > 99) {
            throw new IllegalArgumentException("cents must be between 0 and 99: " + cents);
        }
        return new Money(Math.addExact(Math.multiplyExact(dollars, 100), cents));
    }

    /**
     * @return the amount in cents
     */
    public long getCents() {
        return cents;
    }

    /**
     * Adds another amount to this one.
     *
     * @param other the amount to add
     * @return the sum
     * @throws ArithmeticException if the result overflows
     */
    public Money add(Money other) {
        Objects.requireNonNull(other, "other");
        return new Money(Math.addExact(cents, other.cents));
    }

    /**
     * Subtracts another amount from this one.
     *
     * @param other the amount to subtract
     * @return the difference
     * @throws ArithmeticException if the result overflows
     */
    public Money subtract(Money other) {
        Objects.requireNonNull(other, "other");
        return new Money(Math.subtractExact(cents, other.cents));
    }

    /**
     * Multiplies this amount by a whole number, e.g. a per-minute rate by a number of minutes.
     *
     * @param factor the multiplier
     * @return the product
     * @throws ArithmeticException if the result overflows
     */
    public Money multiply(long factor) {
        return new Money(Math.multiplyExact(cents, factor));
    }

    @Override
    public int compareTo(Money other) {
        return Long.compare(cents, other.cents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }
        return cents == ((Money) o).cents;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(cents);
    }

    /**
     * Formats the amount as dollars, e.g. {@code $2.50} or {@code -$0.75}.
     */
    @Override
    public String toString() {
        long abs = Math.abs(cents);
        String sign = cents < 0 ? "-" : "";
        return String.format("%s$%d.%02d", sign, abs / 100, abs % 100);
    }
}
