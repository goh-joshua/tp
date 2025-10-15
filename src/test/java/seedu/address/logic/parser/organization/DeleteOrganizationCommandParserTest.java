package seedu.address.logic.parser.organization;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.organization.DeleteOrganizationCommand;
import seedu.address.model.organization.OrganizationName;

/**
 * Unit tests for {@code DeleteOrganizationCommandParser}.
 */
public class DeleteOrganizationCommandParserTest {

    private static final String VALID_ORG = "Nike";
    private static final String ORG_DESC = " " + PREFIX_ORG + VALID_ORG;
    private static final String INVALID_ORG = " " + PREFIX_ORG + "@@@"; // invalid symbols
    private final DeleteOrganizationCommandParser parser = new DeleteOrganizationCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteOrganizationCommand() {
        DeleteOrganizationCommand expectedCommand =
                new DeleteOrganizationCommand(new OrganizationName(VALID_ORG));

        assertParseSuccess(parser, ORG_DESC, expectedCommand);
    }

    @Test
    public void parse_missingPrefix_throwsParseException() {
        assertParseFailure(parser,
                VALID_ORG,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOrganizationCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidOrgName_throwsParseException() {
        assertParseFailure(parser,
                INVALID_ORG,
                OrganizationName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser,
                "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOrganizationCommand.MESSAGE_USAGE));
    }
}
