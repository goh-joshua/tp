package seedu.address.model.athlete;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.athlete.Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.address.model.athlete.Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> seedu.address.model.athlete.Phone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(seedu.address.model.athlete.Phone.isValidPhone("")); // empty string
        assertFalse(seedu.address.model.athlete.Phone.isValidPhone(" ")); // spaces only
        assertFalse(seedu.address.model.athlete.Phone.isValidPhone("91")); // less than 8 numbers
        assertFalse(seedu.address.model.athlete.Phone.isValidPhone("phone")); // non-numeric
        assertFalse(seedu.address.model.athlete.Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(seedu.address.model.athlete.Phone.isValidPhone("9312 1534")); // spaces within digits
        assertFalse(seedu.address.model.athlete.Phone.isValidPhone("9312111534")); // more than 9 numbers

        // valid phone numbers
        assertTrue(seedu.address.model.athlete.Phone.isValidPhone("91234567"));
        assertTrue(seedu.address.model.athlete.Phone.isValidPhone("93121534"));
        assertTrue(seedu.address.model.athlete.Phone.isValidPhone("89075432"));
    }

    @Test
    public void equals() {
        seedu.address.model.athlete.Phone phone = new seedu.address.model.athlete.Phone("91234569");

        // same values -> returns true
        assertTrue(phone.equals(new seedu.address.model.athlete.Phone("91234569")));

        // same object -> returns true
        assertTrue(phone.equals(phone));

        // null -> returns false
        assertFalse(phone.equals(null));

        // different types -> returns false
        assertFalse(phone.equals(5.0f));

        // different values -> returns false
        assertFalse(phone.equals(new Phone("92637465")));
    }
}
