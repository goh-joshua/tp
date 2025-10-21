package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Resets all filters and displays all athletes, organizations, and contracts.
 * This command clears any active search filters applied by the find command.
 */
public class RefreshCommand extends Command {

    public static final String COMMAND_WORD = "refresh";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears all active filters and shows all athletes, organizations, and contracts.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "All filters cleared. Showing all athletes, organizations, and contracts.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // Reset all filtered lists to show all items
        model.updateFilteredAthleteList(Model.PREDICATE_SHOW_ALL_ATHLETES);
        model.updateFilteredOrganizationList(Model.PREDICATE_SHOW_ALL_ORGANIZATIONS);
        model.updateFilteredContractList(Model.PREDICATE_SHOW_ALL_CONTRACTS);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // All RefreshCommand instances are considered equal since they have no parameters
        return other instanceof RefreshCommand;
    }

    @Override
    public int hashCode() {
        return COMMAND_WORD.hashCode();
    }
}
