package seedu.address.model.organization;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@code OrganizationName}.
 */
public class OrganizationNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OrganizationName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new OrganizationName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> OrganizationName.isValidName(null));

        // invalid names
        assertFalse(OrganizationName.isValidName("")); // empty string
        assertFalse(OrganizationName.isValidName(" ")); // spaces only
        assertFalse(OrganizationName.isValidName("@Nike")); // invalid starting character
        assertFalse(OrganizationName.isValidName("Nike!")); // invalid symbol
        assertFalse(OrganizationName.isValidName("Procter&Gamble*")); // trailing invalid character
        assertFalse(OrganizationName.isValidName("John#Doe")); // invalid character inside

        // valid names
        assertTrue(OrganizationName.isValidName("Nike")); // simple word
        assertTrue(OrganizationName.isValidName("Team O'Neal")); // apostrophe
        assertTrue(OrganizationName.isValidName("ACME-Corp")); // hyphen
    }

    @Test
    public void equals() {
        OrganizationName name = new OrganizationName("Nike");

        // same values -> returns true
        assertTrue(name.equals(new OrganizationName("Nike")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new OrganizationName("Adidas")));
    }
}
