package seedu.address.model.athlete;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.athlete.Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.address.model.athlete.Email(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> seedu.address.model.athlete.Email.isValidEmail(null));

        // blank email
        String longEmail = "a".repeat(39) + "@example.com";
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("")); // empty string
        assertFalse(seedu.address.model.athlete.Email.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("@example.com")); // missing local part
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("peterjack@exam_ple.com")); // underscore
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("peter jack@example.com")); // spaces
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("peterjack@exam ple.com")); // spaces
        assertFalse(seedu.address.model.athlete.Email.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("peter@jack@example.com")); // '@' symbol
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("-peterjack@example.com")); //  hyphen
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("peterjack-@example.com")); // hyphen
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("peter..jack@example.com"));
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("peterjack@example@com")); // '@' symbol
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("peterjack@.example.com"));
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("peterjack@example.com."));
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("peterjack@-example.com"));
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("peterjack@example.com-"));
        assertFalse(seedu.address.model.athlete.Email.isValidEmail("peterjack@example.c"));
        assertFalse(seedu.address.model.athlete.Email.isValidEmail(longEmail)); // exceeds max length

        // valid email
        String boundaryEmail = "a".repeat(38) + "@example.com";
        assertTrue(seedu.address.model.athlete.Email.isValidEmail("PeterJack_1190@example.com")); // underscore
        assertTrue(seedu.address.model.athlete.Email.isValidEmail("PeterJack.1190@example.com")); // period
        assertTrue(seedu.address.model.athlete.Email.isValidEmail("PeterJack+1190@example.com")); // '+' symbol
        assertTrue(seedu.address.model.athlete.Email.isValidEmail("PeterJack-1190@example.com")); // hyphen
        assertTrue(seedu.address.model.athlete.Email.isValidEmail("a@bc")); // minimal
        assertTrue(seedu.address.model.athlete.Email.isValidEmail("test@localhost")); // alphabets only
        assertTrue(seedu.address.model.athlete.Email.isValidEmail("123@145")); // numeric
        assertTrue(seedu.address.model.athlete.Email.isValidEmail("a1+be.d@example1.com")); // mixture
        assertTrue(seedu.address.model.athlete.Email.isValidEmail("peter_jack@very-very-very-long-example.com"));
        assertTrue(seedu.address.model.athlete.Email.isValidEmail("if.you.dream.it_you.can.do.it@example.com"));
        assertTrue(seedu.address.model.athlete.Email.isValidEmail("e1234567@u.nus.edu")); // more than one period
        assertTrue(seedu.address.model.athlete.Email.isValidEmail(boundaryEmail)); // boundary length
    }

    @Test
    public void equals() {
        seedu.address.model.athlete.Email email = new seedu.address.model.athlete.Email("valid@email");

        // same values -> returns true
        assertTrue(email.equals(new seedu.address.model.athlete.Email("valid@email")));

        // same object -> returns true
        assertTrue(email.equals(email));

        // null -> returns false
        assertFalse(email.equals(null));

        // different types -> returns false
        assertFalse(email.equals(5.0f));

        // different values -> returns false
        assertFalse(email.equals(new Email("other.valid@email")));
    }
}
