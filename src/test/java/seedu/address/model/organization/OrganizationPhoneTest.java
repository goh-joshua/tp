package seedu.address.model.organization;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OrganizationPhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OrganizationPhone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new OrganizationPhone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> OrganizationPhone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(OrganizationPhone.isValidPhone("")); // empty string
        assertFalse(OrganizationPhone.isValidPhone(" ")); // spaces only
        assertFalse(OrganizationPhone.isValidPhone("91")); // too short
        assertFalse(OrganizationPhone.isValidPhone("931215341")); // too long (9 digits)
        assertFalse(OrganizationPhone.isValidPhone("phone")); // non-numeric
        assertFalse(OrganizationPhone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(OrganizationPhone.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers (exactly 8 digits)
        assertTrue(OrganizationPhone.isValidPhone("93121534"));
        assertTrue(OrganizationPhone.isValidPhone("81234567"));
        assertTrue(OrganizationPhone.isValidPhone("99999999"));
    }

    @Test
    public void equals() {
        OrganizationPhone phone = new OrganizationPhone("99999999");

        // same values -> returns true
        assertTrue(phone.equals(new OrganizationPhone("99999999")));

        // same object -> returns true
        assertTrue(phone.equals(phone));

        // null -> returns false
        assertFalse(phone.equals(null));

        // different types -> returns false
        assertFalse(phone.equals(5.0f));

        // different values -> returns false
        assertFalse(phone.equals(new OrganizationPhone("88888888")));
    }
}
