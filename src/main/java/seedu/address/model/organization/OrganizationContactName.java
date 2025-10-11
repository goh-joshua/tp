package seedu.address.model.organization;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Organization's contact person's name in playbook.io.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}.
 */
public class OrganizationContactName {

    public static final String MESSAGE_CONSTRAINTS =
            "Invalid name: Names can only contain letters, spaces, hyphens, and apostrophes.";

    /*
     * The name must contain only letters (upper/lowercase), spaces, apostrophes, and hyphens.
     * Multiple spaces are allowed but will be normalized.
     * Must contain at least one letter.
     */
    public static final String VALIDATION_REGEX = "^[A-Za-z][A-Za-z'\\- ]*[A-Za-z]$|^[A-Za-z]$";

    public final String fullName;

    /**
     * Constructs a {@code OrganizationContactName}.
     *
     * @param name A valid contact name.
     */
    public OrganizationContactName(String name) {
        requireNonNull(name);
        String trimmed = name.trim().replaceAll("\\s+", " ");
        checkArgument(isValidName(trimmed), MESSAGE_CONSTRAINTS);
        fullName = trimmed;
    }

    /**
     * Returns true if a given string is a valid contact name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof OrganizationContactName
                && fullName.equalsIgnoreCase(((OrganizationContactName) other).fullName));
    }

    @Override
    public int hashCode() {
        return fullName.toLowerCase().hashCode();
    }
}
