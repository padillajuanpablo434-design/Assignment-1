package gocycle.pricing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void addSumsCents() {
        Money sum = Money.ofCents(150).add(Money.ofCents(275));
        assertEquals(425, sum.getCents());
    }

    @Test
    void addZeroReturnsEqualAmount() {
        Money amount = Money.of(3, 10);
        assertEquals(amount, amount.add(Money.ZERO));
    }

    @Test
    void multiplyScalesByFactor() {
        Money perMinute = Money.ofCents(15);
        assertEquals(Money.ofCents(450), perMinute.multiply(30));
    }

    @Test
    void multiplyByZeroIsZero() {
        assertEquals(Money.ZERO, Money.of(5, 0).multiply(0));
    }

    @Test
    void operationsDoNotModifyOriginal() {
        Money original = Money.ofCents(100);
        original.add(Money.ofCents(50));
        original.multiply(3);
        assertEquals(100, original.getCents());
    }

    @Test
    void ofCombinesDollarsAndCents() {
        assertEquals(Money.ofCents(250), Money.of(2, 50));
    }

    @Test
    void ofRejectsInvalidCents() {
        assertThrows(IllegalArgumentException.class, () -> Money.of(1, 100));
        assertThrows(IllegalArgumentException.class, () -> Money.of(1, -1));
    }

    @Test
    void toStringFormatsDollarsWithTwoDecimals() {
        assertEquals("$2.50", Money.ofCents(250).toString());
        assertEquals("$0.05", Money.ofCents(5).toString());
        assertEquals("$0.00", Money.ZERO.toString());
        assertEquals("$1234.00", Money.ofCents(123400).toString());
    }

    @Test
    void toStringFormatsNegativeAmounts() {
        assertEquals("-$0.75", Money.ofCents(-75).toString());
    }

    @Test
    void equalsComparesByValue() {
        assertEquals(Money.ofCents(99), Money.ofCents(99));
        assertEquals(Money.ofCents(99).hashCode(), Money.ofCents(99).hashCode());
        assertNotEquals(Money.ofCents(99), Money.ofCents(100));
    }
}
