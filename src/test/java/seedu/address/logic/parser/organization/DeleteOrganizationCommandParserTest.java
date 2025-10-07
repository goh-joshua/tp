package seedu.address.logic.parser.organization;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.organization.DeleteOrganizationCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteOrganizationCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteOrganizationCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteOrganizationCommandParserTest {

    private final DeleteOrganizationCommandParser parser = new DeleteOrganizationCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteOrganizationCommand() {
        Index firstIndex = Index.fromOneBased(1);
        assertParseSuccess(parser, "1", new DeleteOrganizationCommand(firstIndex));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOrganizationCommand.MESSAGE_USAGE));
    }
}
