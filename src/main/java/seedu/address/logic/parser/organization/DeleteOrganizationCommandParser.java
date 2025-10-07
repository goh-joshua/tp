package seedu.address.logic.parser.organization;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.organization.DeleteOrganizationCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteOrganizationCommand object
 */
public class DeleteOrganizationCommandParser implements Parser<DeleteOrganizationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteOrganizationCommand
     * and returns a DeleteOrganizationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteOrganizationCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteOrganizationCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOrganizationCommand.MESSAGE_USAGE), pe);
        }
    }

}
