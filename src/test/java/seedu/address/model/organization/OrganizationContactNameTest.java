package seedu.address.model.organization;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@code OrganizationContactName}.
 */
public class OrganizationContactNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OrganizationContactName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new OrganizationContactName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> OrganizationContactName.isValidName(null));

        // invalid names
        assertFalse(OrganizationContactName.isValidName("")); // empty string
        assertFalse(OrganizationContactName.isValidName(" ")); // spaces only
        assertFalse(OrganizationContactName.isValidName("123")); // digits only
        assertFalse(OrganizationContactName.isValidName("John@Doe")); // special character
        assertFalse(OrganizationContactName.isValidName("John#Doe")); // symbol
        assertFalse(OrganizationContactName.isValidName("John_Doe")); // underscore
        assertFalse(OrganizationContactName.isValidName("John!")); // punctuation

        // valid names
        assertTrue(OrganizationContactName.isValidName("John Doe")); // regular two-word name
        assertTrue(OrganizationContactName.isValidName("Jean-Pierre")); // hyphenated
        assertTrue(OrganizationContactName.isValidName("O'Neal")); // apostrophe
        assertTrue(OrganizationContactName.isValidName("LeBron James")); // space
        assertTrue(OrganizationContactName.isValidName("Mary Jane Watson")); // multiple words
    }

    @Test
    public void equals() {
        OrganizationContactName name = new OrganizationContactName("John Doe");

        // same values -> returns true
        assertTrue(name.equals(new OrganizationContactName("John Doe")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new OrganizationContactName("Jane Doe")));
    }
}
