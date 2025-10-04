package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPORT;


import seedu.address.logic.commands.AddAthleteCommand;
import seedu.address.model.athlete.Athlete;

/**
 * A utility class for Athlete.
 */
public class AthleteUtil {

    /**
     * Returns an add command string for adding the {@code athlete}.
     */
    public static String getAddAthleteCommand(Athlete athlete) {
        return AddAthleteCommand.COMMAND_WORD + " " + getAthleteDetails(athlete);
    }

    /**
     * Returns the part of command string for the given {@code athlete}'s details.
     */
    public static String getAthleteDetails(Athlete athlete) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + athlete.getName().fullName + " ");
        sb.append(PREFIX_SPORT + athlete.getSport().value + " ");
        sb.append(PREFIX_AGE + athlete.getAge().value + " ");
        sb.append(PREFIX_PHONE + athlete.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + athlete.getEmail().value + " ");
        return sb.toString();
    }
}
