package seedu.address.model.organization;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Locale;

/**
 * Represents an Organization's name in playbook.io.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}.
 */
public class OrganizationName {

    public static final String MESSAGE_CONSTRAINTS =
            "Error: Names should only contain alphanumeric characters, spaces, hyphens, apostrophes, or ampersands, "
                    + "must start with an alphanumeric character, cannot be blank, and be at most 50 characters.";

    /*
     * Organization names can include letters, numbers, spaces, apostrophes, hyphens, and ampersands.
     * Must start with an alphanumeric character and be at most 50 characters.
     */
    public static final String VALIDATION_REGEX = "^(?=.{1,50}$)[A-Za-z0-9][A-Za-z0-9' &-]*$";

    public final String fullOrganizationName;

    /**
     * Constructs a {@code OrganizationName}.
     *
     * @param name A valid organization name.
     */
    public OrganizationName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullOrganizationName = name.trim().replaceAll("\\s+", " ");
    }

    /**
     * Returns true if a given string is a valid organization name.
     */
    public static boolean isValidName(String test) {
        return test.trim().replaceAll("\\s+", " ").matches(VALIDATION_REGEX);
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
        return fullOrganizationName.toLowerCase(Locale.ROOT).hashCode();
    }
}
