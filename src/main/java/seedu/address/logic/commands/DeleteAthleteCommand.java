package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPORT;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.AthleteMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AthleteModel;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.Name;
import seedu.address.model.athlete.Sport;


/**
 * Deletes an athlete identified using it's displayed index from playbook.io.
 */
public class DeleteAthleteCommand extends AthleteCommand {

    public static final String COMMAND_WORD = "delete-a";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the athlete identified by name and sport.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_SPORT + "SPORT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "LeBron James "
            + PREFIX_SPORT + "Basketball";

    public static final String MESSAGE_DELETE_ATHLETE_SUCCESS = "Deleted Athlete: %1$s";

    private final Name name;
    private final Sport sport;

    public DeleteAthleteCommand(Name name, Sport sport) {
        this.name = name;
        this.sport = sport;
    }

    @Override
    public CommandResult execute(AthleteModel model) throws CommandException {
        requireNonNull(model);
        List<Athlete> lastShownList = model.getFilteredAthleteList();

        Athlete athleteToDelete = lastShownList.stream()
                .filter(a -> a.getName().equals(name)
                        && a.getSport().equals(sport))
                .findFirst()
                .orElse(null);

        if (athleteToDelete == null) {
            throw new CommandException(AthleteMessages.MESSAGE_INVALID_ATHLETE);
        }

        model.deleteAthlete(athleteToDelete);
        return new CommandResult(String.format(
                MESSAGE_DELETE_ATHLETE_SUCCESS,
                AthleteMessages.format(athleteToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteAthleteCommand)) {
            return false;
        }

        DeleteAthleteCommand otherDeleteAthleteCommand = (DeleteAthleteCommand) other;
        return name.equals(otherDeleteAthleteCommand.name)
                && sport.equals(otherDeleteAthleteCommand.sport);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("sport", sport)
                .toString();
    }
}
