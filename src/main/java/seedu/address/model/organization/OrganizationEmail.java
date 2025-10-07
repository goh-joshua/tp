package seedu.address.model.organization;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Organization's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}.
 */
public class OrganizationEmail {

    public static final String MESSAGE_CONSTRAINTS =
            "Invalid email format. Example: john.doe@nike.com";

    /*
     * Basic email validation: one '@', valid local and domain parts.
     * Allows letters, digits, underscores, hyphens, and dots.
     */
    public static final String VALIDATION_REGEX = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$";

    public final String value;

    /**
     * Constructs an {@code OrganizationEmail}.
     *
     * @param email A valid email address.
     */
    public OrganizationEmail(String email) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        value = email.trim();
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof OrganizationEmail
                && value.equalsIgnoreCase(((OrganizationEmail) other).value));
    }

    @Override
    public int hashCode() {
        return value.toLowerCase().hashCode();
    }
}
