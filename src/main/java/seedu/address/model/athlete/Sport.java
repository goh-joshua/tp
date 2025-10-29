package seedu.address.model.athlete;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Locale;

/**
 * Represents an Athlete's sport in playbook.io.
 * Guarantees: immutable; is valid as declared in {@link #isValidSport(String)}.
 */
public class Sport {

    public static final String MESSAGE_CONSTRAINTS =
            "Error: Sport names should only contain alphabetic characters and spaces, "
                    + "should not be blank, and be at most 50 characters.";
    public static final String VALIDATION_REGEX = "^(?=.{1,50}$)[A-Za-z][A-Za-z ]*$";

    public final String value;

    /**
     * Constructs a {@code Sport}.
     *
     * @param sport A valid sport name.
     */
    public Sport(String sport) {
        requireNonNull(sport);
        checkArgument(isValidSport(sport), MESSAGE_CONSTRAINTS);
        value = sport.trim().replaceAll("\\s+", " ");
    }

    /**
     * Returns true if a given string is a valid sport name.
     */
    public static boolean isValidSport(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Sport)) {
            return false;
        }

        Sport otherSport = (Sport) other;
        return value.equalsIgnoreCase(otherSport.value);
    }

    @Override
    public int hashCode() {
        return value.toLowerCase(Locale.ROOT).hashCode();
    }
}
