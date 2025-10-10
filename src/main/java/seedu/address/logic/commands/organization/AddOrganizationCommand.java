package seedu.address.logic.commands.organization;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.organization.Organization;

/**
 * Adds an organization to the address book.
 */
public class AddOrganizationCommand extends Command {

    public static final String COMMAND_WORD = "add-org";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an organization to the address book. "
            + "Parameters: "
            + PREFIX_ORG + "ORGANIZATION_NAME "
            + PREFIX_CONTACT_NAME + "CONTACT_NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ORG + "Nike "
            + PREFIX_CONTACT_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "john.doe@nike.com";

    public static final String MESSAGE_SUCCESS = "New organization added: %1$s";
    public static final String MESSAGE_DUPLICATE_ORGANIZATION = "This organization already exists in the address book";

    private final Organization toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Organization}
     */
    public AddOrganizationCommand(Organization organization) {
        requireNonNull(organization);
        toAdd = organization;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasOrganization(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORGANIZATION);
        }
        model.addOrganization(toAdd);

        return new CommandResult(String.format(
                MESSAGE_SUCCESS,
                Messages.format(toAdd)
        ));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddOrganizationCommand)) {
            return false;
        }

        AddOrganizationCommand otherAddCommand = (AddOrganizationCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
