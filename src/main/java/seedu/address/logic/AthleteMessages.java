package seedu.address.logic;

import seedu.address.model.athlete.Athlete;

/**
 * Container for user visible messages.
 */
public class AthleteMessages {

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Error: Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_ATHLETE = "Error: Athlete not found";
    public static final String MESSAGE_EXISTING_CONTRACT = "Error: Athlete has existing contracts";

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
