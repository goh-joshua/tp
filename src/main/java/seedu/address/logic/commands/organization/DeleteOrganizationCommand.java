package seedu.address.logic.commands.organization;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.organization.Organization;
import seedu.address.model.organization.OrganizationName;

/**
 * Deletes an organization identified by its name from playbook.io.
 */
public class DeleteOrganizationCommand extends Command {

    public static final String COMMAND_WORD = "delete-o";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the organization identified by its name.\n"
            + "Parameters: o/ORGANIZATION_NAME\n"
            + "Example: " + COMMAND_WORD + " o/Nike";

    public static final String MESSAGE_DELETE_ORGANIZATION_SUCCESS = "Deleted Organization: %1$s";
    public static final String MESSAGE_ORGANIZATION_NOT_FOUND = "Error: No organization found with name '%1$s'.";

    private final OrganizationName targetName;

    public DeleteOrganizationCommand(OrganizationName targetName) {
        requireNonNull(targetName);
        this.targetName = targetName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Organization> lastShownList = model.getFilteredOrganizationList();

        Organization organizationToDelete = lastShownList.stream()
                .filter(org -> org.getName().equals(targetName))
                .findFirst()
                .orElse(null);

        if (organizationToDelete == null) {
            throw new CommandException(String.format(MESSAGE_ORGANIZATION_NOT_FOUND, targetName));
        }

        model.deleteOrganization(organizationToDelete);
        return new CommandResult(String.format(
                MESSAGE_DELETE_ORGANIZATION_SUCCESS, Messages.format(organizationToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteOrganizationCommand
                && targetName.equals(((DeleteOrganizationCommand) other).targetName));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetName", targetName)
                .toString();
    }
}
