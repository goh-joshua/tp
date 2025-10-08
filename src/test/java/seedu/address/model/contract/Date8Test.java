package seedu.address.model.contract;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class Date8Test {

    @Test
    void constructor_invalid_throwsIllegalArgumentException() {
        assertThrows(NullPointerException.class, () -> new Date8(null));
        assertThrows(IllegalArgumentException.class, () -> new Date8(""));
        assertThrows(IllegalArgumentException.class, () -> new Date8("010120"));     // too short
        assertThrows(IllegalArgumentException.class, () -> new Date8("01-01-2025")); // non-digit
        assertThrows(IllegalArgumentException.class, () -> new Date8("31022025"));   // invalid date
        assertThrows(IllegalArgumentException.class, () -> new Date8("29022025"));   // non-leap year Feb 29
    }

    @Test
    void isValidDate8() {
        assertFalse(Date8.isValidDate8(null));
        assertFalse(Date8.isValidDate8(""));
        assertFalse(Date8.isValidDate8("010120"));
        assertFalse(Date8.isValidDate8("31-12-2024"));
        assertFalse(Date8.isValidDate8("31022025"));

        assertTrue(Date8.isValidDate8("01012025"));
        assertTrue(Date8.isValidDate8("29022024")); // leap day
    }

    @Test
    void toLocalDate_ok() {
        Date8 d = new Date8("01012025");
        assertEquals(LocalDate.of(2025, 1, 1), d.toLocalDate());
    }

    @Test
    void equalsAndHashCode() {
        Date8 d1 = new Date8("01012025");
        Date8 d2 = new Date8("01012025");
        Date8 d3 = new Date8("02012025");

        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
        assertNotEquals(d1, d3);
        assertNotEquals(null, d1);
    }

    @Test
    void toString_ok() {
        assertEquals("31122024", new Date8("31122024").toString());
    }
}
