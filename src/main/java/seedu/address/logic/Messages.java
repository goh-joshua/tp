package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.organization.Organization;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Error: Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Error: Invalid command format! \n%1$s";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Error: Multiple values specified for the following single-valued field(s): ";

    // === Organization messages ===

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
     * Formats the {@code organization} for display to the user.
     */
    public static String format(Organization organization) {
        final StringBuilder builder = new StringBuilder();
        builder.append(organization.getName())
                .append("; Phone: ")
                .append(organization.getPhone())
                .append("; Email: ")
                .append(organization.getEmail());
        return builder.toString();
    }
}
