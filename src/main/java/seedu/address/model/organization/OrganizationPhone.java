package seedu.address.model.organization;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Organization's phone number in playbook.io.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}.
 */
public class OrganizationPhone {

    public static final String MESSAGE_CONSTRAINTS =
            "Error: Phone numbers should only contain numbers, and be exactly 8 digits long, "
                    + "and start with 6, 8, or 9.";
    public static final String VALIDATION_REGEX = "^[689]\\d{7}$";

    public final String value;

    /**
     * Constructs a {@code OrganizationPhone}.
     *
     * @param phone A valid phone number.
     */
    public OrganizationPhone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone.trim();
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof OrganizationPhone
                && value.equals(((OrganizationPhone) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
