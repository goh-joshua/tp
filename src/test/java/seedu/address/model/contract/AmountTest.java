package seedu.address.model.contract;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AmountTest {

    @Test
    void constructor_nonPositive_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Amount(0));
        assertThrows(IllegalArgumentException.class, () -> new Amount(-1));
    }

    @Test
    void isValidAmount() {
        assertFalse(Amount.isValidAmount(null));
        assertFalse(Amount.isValidAmount(""));
        assertFalse(Amount.isValidAmount(" "));
        assertFalse(Amount.isValidAmount("abc"));
        assertFalse(Amount.isValidAmount("-10"));
        assertFalse(Amount.isValidAmount("0"));

        assertTrue(Amount.isValidAmount("1"));
        assertTrue(Amount.isValidAmount("2500"));
        assertTrue(Amount.isValidAmount("  42  "));
    }

    @Test
    void equalsAndHashCode() {
        Amount a = new Amount(10);
        Amount b = new Amount(10);
        Amount c = new Amount(11);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
        assertNotEquals(null, a);
    }

    @Test
    void toString_ok() {
        assertEquals("123", new Amount(123).toString());
    }
}
