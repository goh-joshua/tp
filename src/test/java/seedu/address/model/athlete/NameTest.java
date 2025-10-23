package seedu.address.model.athlete;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.athlete.Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.address.model.athlete.Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> seedu.address.model.athlete.Name.isValidName(null));

        // invalid name
        assertFalse(seedu.address.model.athlete.Name.isValidName("")); // empty string
        assertFalse(seedu.address.model.athlete.Name.isValidName(" ")); // spaces only
        assertFalse(seedu.address.model.athlete.Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(seedu.address.model.athlete.Name.isValidName("peter*")); // contains non-alphanumeric characters
        assertFalse(seedu.address.model.athlete.Name.isValidName("12345"));

        // valid name
        assertTrue(seedu.address.model.athlete.Name.isValidName("peter jack")); // alphabets only
        assertTrue(seedu.address.model.athlete.Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(seedu.address.model.athlete.Name.isValidName("O'Neal"));
        assertTrue(seedu.address.model.athlete.Name.isValidName("O-Neal"));
    }

    @Test
    public void equals() {
        seedu.address.model.athlete.Name name = new seedu.address.model.athlete.Name("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new seedu.address.model.athlete.Name("Valid Name")));

        // name differs in casing -> returns true
        assertTrue(name.equals(new seedu.address.model.athlete.Name("valid name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new Name("Other Valid Name")));
    }
}
