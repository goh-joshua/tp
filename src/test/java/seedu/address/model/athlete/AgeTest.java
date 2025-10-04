package seedu.address.model.athlete;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AgeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.athlete.Age(null));
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        String invalidAge = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.address.model.athlete.Age(invalidAge));
    }

    @Test
    public void isValidAge() {
        // null age
        assertThrows(NullPointerException.class, () -> seedu.address.model.athlete.Age.isValidAge(null));

        // blank age
        assertFalse(seedu.address.model.athlete.Age.isValidAge("")); // empty string
        assertFalse(seedu.address.model.athlete.Age.isValidAge(" ")); // spaces only

        // invalid parts
        assertFalse(seedu.address.model.athlete.Age.isValidAge(" 12")); // leading space
        assertFalse(seedu.address.model.athlete.Age.isValidAge("0")); // out of valid range
        assertFalse(seedu.address.model.athlete.Age.isValidAge("three")); // letters
        assertFalse(seedu.address.model.athlete.Age.isValidAge("2-3")); // dashes

        // valid age
        assertTrue(seedu.address.model.athlete.Age.isValidAge("12"));
        assertTrue(seedu.address.model.athlete.Age.isValidAge("19"));
        assertTrue(seedu.address.model.athlete.Age.isValidAge("29"));

    }

    @Test
    public void equals() {
        seedu.address.model.athlete.Age age = new seedu.address.model.athlete.Age("12");

        // same values -> returns true
        assertTrue(age.equals(new seedu.address.model.athlete.Age("12")));

        // same object -> returns true
        assertTrue(age.equals(age));

        // null -> returns false
        assertFalse(age.equals(null));

        // different types -> returns false
        assertFalse(age.equals("Golf"));

        // different values -> returns false
        assertFalse(age.equals(new Age("90")));
    }
}
