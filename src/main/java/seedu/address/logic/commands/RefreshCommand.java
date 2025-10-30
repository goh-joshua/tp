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

    public static final String MESSAGE_SUCCESS = "All filters cleared. "
            + "Showing all athletes, organizations, and contracts.";

    /**
     * Executes the refresh command to clear all filters and display all entities.
     *
     * @param model The model containing the filtered lists. Cannot be null.
     * @return A CommandResult with a success message.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // Reset all filtered lists to show all items
        model.updateFilteredAthleteList(Model.PREDICATE_SHOW_ALL_ATHLETES);
        model.updateFilteredOrganizationList(Model.PREDICATE_SHOW_ALL_ORGANIZATIONS);
        model.updateFilteredContractList(Model.PREDICATE_SHOW_ALL_CONTRACTS);

        assert model.getFilteredAthleteList().size() == model.getAddressBook().getAthleteList().size()
                : "Filtered athlete list should match full list size";
        assert model.getFilteredOrganizationList().size() == model.getAddressBook().getOrganizationList().size()
                : "Filtered organization list should match full list size";
        assert model.getFilteredContractList().size() == model.getContractList().getContractList().size()
                : "Filtered contract list should match full list size";

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Returns true if the other object is also a RefreshCommand.
     * All RefreshCommand instances are considered equal since they have no parameters.
     *
     * @param other The other object to compare with.
     * @return True if the other object is a RefreshCommand, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        // All RefreshCommand instances are considered equal since they have no parameters
        return other instanceof RefreshCommand;
    }

    /**
     * Returns the hash code for this RefreshCommand.
     * All RefreshCommand instances have the same hash code.
     *
     * @return The hash code based on the command word.
     */
    @Override
    public int hashCode() {
        return COMMAND_WORD.hashCode();
    }
}
