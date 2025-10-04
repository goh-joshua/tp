package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AthleteModel;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class AthleteCommand {

    /**
     * Executes the command and returns the result message.
     *
     * @param athleteModel {@code AthleteModel} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(AthleteModel athleteModel) throws CommandException;

}
