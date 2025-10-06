package seedu.address.model.organization;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Organization's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}.
 */
public class OrganizationName {

    public static final String MESSAGE_CONSTRAINTS =
            "Invalid organisation: Only letters, numbers, spaces, apostrophes ('), ampersands (&), and hyphens (-) are allowed.";

    /*
     * Organization names can include letters, digits, spaces, apostrophes, ampersands, and hyphens.
     * Must contain at least one alphanumeric character and cannot be blank.
     */
    public static final String VALIDATION_REGEX = "^(?=.*[A-Za-z0-9])[A-Za-z0-9 '&-]+$";

    public final String fullOrganizationName;

    /**
     * Constructs a {@code OrganizationName}.
     *
     * @param name A valid organization name.
     */
    public OrganizationName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullOrganizationName = name.trim();
    }

    /**
     * Returns true if a given string is a valid organization name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullOrganizationName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof OrganizationName
                && fullOrganizationName.equalsIgnoreCase(((OrganizationName) other).fullOrganizationName));
    }

    @Override
    public int hashCode() {
        return fullOrganizationName.toLowerCase().hashCode();
    }
}