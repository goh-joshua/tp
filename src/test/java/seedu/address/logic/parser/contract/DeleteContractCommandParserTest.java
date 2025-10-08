package seedu.address.logic.parser.contract;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.contract.DeleteContractCommand;
import seedu.address.model.contract.Date8;
import seedu.address.model.person.Name;

/**
 * Contains unit tests for {@link DeleteContractCommandParser}.
 */
public class DeleteContractCommandParserTest {

    private static final String VALID_ATHLETE_NAME = "Lionel Messi";
    private static final String VALID_ORG_NAME = "Inter Miami CF";
    private static final String VALID_START = "01012024";
    private static final String VALID_END = "01012025";

    private static final String NAME_DESC = " " + PREFIX_NAME + VALID_ATHLETE_NAME;
    private static final String ORG_DESC = " " + PREFIX_ORG + VALID_ORG_NAME;
    private static final String START_DESC = " " + PREFIX_START_DATE + VALID_START;
    private static final String END_DESC = " " + PREFIX_END_DATE + VALID_END;

    private static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "1234";
    private static final String INVALID_ORG_DESC = " " + PREFIX_ORG + "@@";
    private static final String INVALID_START_DESC = " " + PREFIX_START_DATE + "32132024";
    private static final String INVALID_END_DESC = " " + PREFIX_END_DATE + "abcd2024";

    private final DeleteContractCommandParser parser = new DeleteContractCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        DeleteContractCommand expected = new DeleteContractCommand(
                new Name(VALID_ATHLETE_NAME),
                new Name(VALID_ORG_NAME),
                new Date8(VALID_START),
                new Date8(VALID_END));

        assertParseSuccess(parser,
                NAME_DESC + ORG_DESC + START_DESC + END_DESC,
                expected);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteContractCommand.MESSAGE_USAGE);

        assertParseFailure(parser, ORG_DESC + START_DESC + END_DESC, expectedMessage);
        assertParseFailure(parser, NAME_DESC + START_DESC + END_DESC, expectedMessage);
        assertParseFailure(parser, NAME_DESC + ORG_DESC + END_DESC, expectedMessage);
        assertParseFailure(parser, NAME_DESC + ORG_DESC + START_DESC, expectedMessage);
        assertParseFailure(parser, "preamble" + NAME_DESC + ORG_DESC + START_DESC + END_DESC, expectedMessage);
    }

    @Test
    public void parse_invalidValues_failure() {
        assertParseFailure(parser,
                INVALID_NAME_DESC + ORG_DESC + START_DESC + END_DESC,
                Name.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                NAME_DESC + INVALID_ORG_DESC + START_DESC + END_DESC,
                Name.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                NAME_DESC + ORG_DESC + INVALID_START_DESC + END_DESC,
                Date8.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                NAME_DESC + ORG_DESC + START_DESC + INVALID_END_DESC,
                Date8.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                INVALID_NAME_DESC + INVALID_ORG_DESC + INVALID_START_DESC + INVALID_END_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_duplicatePrefixes_failure() {
        assertParseFailure(parser,
                NAME_DESC + NAME_DESC + ORG_DESC + START_DESC + END_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        assertParseFailure(parser,
                NAME_DESC + ORG_DESC + ORG_DESC + START_DESC + END_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ORG));

        assertParseFailure(parser,
                NAME_DESC + ORG_DESC + START_DESC + START_DESC + END_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_DATE));

        assertParseFailure(parser,
                NAME_DESC + ORG_DESC + START_DESC + END_DESC + END_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_END_DATE));

        assertParseFailure(parser,
                NAME_DESC + ORG_DESC + START_DESC + END_DESC
                        + NAME_DESC + ORG_DESC + START_DESC + END_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(
                        PREFIX_NAME, PREFIX_ORG, PREFIX_START_DATE, PREFIX_END_DATE));
    }
}
