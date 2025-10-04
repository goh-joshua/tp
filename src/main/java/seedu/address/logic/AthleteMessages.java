package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.athlete.Athlete;

/**
 * Container for user visible messages.
 */
public class AthleteMessages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_ATHLETE = "No athlete found";
    public static final String MESSAGE_ATHLETES_LISTED_OVERVIEW = "%1$d athletes listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code athlete} for display to the user.
     */
    public static String format(Athlete athlete) {
        final StringBuilder builder = new StringBuilder();
        builder.append(athlete.getName())
                .append("; Sport: ")
                .append(athlete.getSport())
                .append("; Age: ")
                .append(athlete.getAge())
                .append("; Phone: ")
                .append(athlete.getPhone())
                .append("; Email: ")
                .append(athlete.getEmail());
        return builder.toString();
    }

}
