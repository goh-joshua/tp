package seedu.address.logic.commands.athlete;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPORT;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.AthleteMessages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.athlete.Athlete;

/**
 * Adds an athlete to the address book.
 */
public class AddAthleteCommand extends Command {

    public static final String COMMAND_WORD = "add-a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an athlete to playbook.io. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_SPORT + "SPORT "
            + PREFIX_AGE + "AGE "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "LeBron James "
            + PREFIX_SPORT + "Basketball "
            + PREFIX_AGE + "40 "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "James@example.com ";

    public static final String MESSAGE_SUCCESS = "New athlete added: %1$s";
    public static final String MESSAGE_DUPLICATE_ATHLETE = "This athlete already exists in playbook.io";

    private final Athlete toAdd;

    /**
     * Creates an AddAthleteCommand to add the specified {@code Athlete}
     */
    public AddAthleteCommand(Athlete athlete) {
        requireNonNull(athlete);
        toAdd = athlete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAthlete(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ATHLETE);
        }

        model.addAthlete(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, AthleteMessages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAthleteCommand)) {
            return false;
        }

        AddAthleteCommand otherAddAthleteCommand = (AddAthleteCommand) other;
        return toAdd.equals(otherAddAthleteCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
