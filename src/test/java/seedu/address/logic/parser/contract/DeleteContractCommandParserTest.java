package seedu.address.logic.parser.contract;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.contract.DeleteContractCommand;
import seedu.address.model.athlete.Name;
import seedu.address.model.athlete.Sport;
import seedu.address.model.contract.Amount;
import seedu.address.model.contract.Date8;
import seedu.address.model.organization.OrganizationName;

/**
 * Contains unit tests for {@link DeleteContractCommandParser}.
 */
public class DeleteContractCommandParserTest {

    private static final String VALID_ATHLETE_NAME = "Lionel Messi";
    private static final String VALID_ORG_NAME = "Inter Miami CF";
    private static final String VALID_START = "01012024";
    private static final String VALID_END = "01012025";
    private static final String VALID_SPORT = "Football";
    private static final String VALID_AMOUNT = "130000000";

    private static final String NAME_DESC = " " + PREFIX_NAME + VALID_ATHLETE_NAME;
    private static final String ORG_DESC = " " + PREFIX_ORG + VALID_ORG_NAME;
    private static final String START_DESC = " " + PREFIX_START_DATE + VALID_START;
    private static final String END_DESC = " " + PREFIX_END_DATE + VALID_END;
    private static final String SPORT_DESC = " " + PREFIX_SPORT + VALID_SPORT;
    private static final String AMOUNT_DESC = " " + PREFIX_AMOUNT + VALID_AMOUNT;

    private static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "1234";
    private static final String INVALID_ORG_DESC = " " + PREFIX_ORG + "@@";
    private static final String INVALID_START_DESC = " " + PREFIX_START_DATE + "32132024";
    private static final String INVALID_END_DESC = " " + PREFIX_END_DATE + "abcd2024";
    private static final String INVALID_SPORT_DESC = " " + PREFIX_SPORT + "123";
    private static final String INVALID_AMOUNT_DESC = " " + PREFIX_AMOUNT + "12x34";

    private final DeleteContractCommandParser parser = new DeleteContractCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        DeleteContractCommand expected = new DeleteContractCommand(
                new Name(VALID_ATHLETE_NAME),
                new OrganizationName(VALID_ORG_NAME),
                new Date8(VALID_START),
                new Date8(VALID_END),
                new Sport(VALID_SPORT),
                new Amount(Integer.parseInt(VALID_AMOUNT)));

        assertParseSuccess(parser,
                NAME_DESC + ORG_DESC + START_DESC + END_DESC + SPORT_DESC + AMOUNT_DESC,
                expected);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        // Message should be the usage string
        String expectedMessage = String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteContractCommand.MESSAGE_USAGE);

        // each missing one of the required prefixes (any one)
        assertParseFailure(parser, ORG_DESC + START_DESC + END_DESC + SPORT_DESC + AMOUNT_DESC, expectedMessage);
        assertParseFailure(parser, NAME_DESC + START_DESC + END_DESC + SPORT_DESC + AMOUNT_DESC, expectedMessage);
        assertParseFailure(parser, NAME_DESC + ORG_DESC + END_DESC + SPORT_DESC + AMOUNT_DESC, expectedMessage);
        assertParseFailure(parser, NAME_DESC + ORG_DESC + START_DESC + SPORT_DESC + AMOUNT_DESC, expectedMessage);

        // preamble should not be present
        assertParseFailure(parser, "preamble" + NAME_DESC + ORG_DESC + START_DESC + END_DESC + SPORT_DESC + AMOUNT_DESC,
                expectedMessage);

    }

    @Test
    public void parse_invalidValues_failure() {
        // invalid name
        assertParseFailure(parser,
                INVALID_NAME_DESC + ORG_DESC + START_DESC + END_DESC + SPORT_DESC + AMOUNT_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // invalid org
        assertParseFailure(parser,
                NAME_DESC + INVALID_ORG_DESC + START_DESC + END_DESC + SPORT_DESC + AMOUNT_DESC,
                OrganizationName.MESSAGE_CONSTRAINTS);

        // invalid start date
        assertParseFailure(parser,
                NAME_DESC + ORG_DESC + INVALID_START_DESC + END_DESC + SPORT_DESC + AMOUNT_DESC,
                Date8.MESSAGE_CONSTRAINTS);

        // invalid end date
        assertParseFailure(parser,
                NAME_DESC + ORG_DESC + START_DESC + INVALID_END_DESC + SPORT_DESC + AMOUNT_DESC,
                Date8.MESSAGE_CONSTRAINTS);

        // invalid sport
        assertParseFailure(parser,
                NAME_DESC + ORG_DESC + START_DESC + END_DESC + INVALID_SPORT_DESC + AMOUNT_DESC,
                Sport.MESSAGE_CONSTRAINTS);

        // invalid amount
        assertParseFailure(parser,
                NAME_DESC + ORG_DESC + START_DESC + END_DESC + SPORT_DESC + INVALID_AMOUNT_DESC,
                Amount.MESSAGE_CONSTRAINTS);

        // multiple invalids - any one of the relevant messages is acceptable; assert one consistently
        assertParseFailure(parser,
                INVALID_NAME_DESC + INVALID_ORG_DESC + INVALID_START_DESC + INVALID_END_DESC + INVALID_SPORT_DESC
                        + INVALID_AMOUNT_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_duplicatePrefixes_failure() {
        // parser verifies duplicate for name/org/start/end (as implemented)
        assertParseFailure(parser,
                NAME_DESC + NAME_DESC + ORG_DESC + START_DESC + END_DESC + SPORT_DESC + AMOUNT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        assertParseFailure(parser,
                NAME_DESC + ORG_DESC + ORG_DESC + START_DESC + END_DESC + SPORT_DESC + AMOUNT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ORG));

        assertParseFailure(parser,
                NAME_DESC + ORG_DESC + START_DESC + START_DESC + END_DESC + SPORT_DESC + AMOUNT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_DATE));

        assertParseFailure(parser,
                NAME_DESC + ORG_DESC + START_DESC + END_DESC + END_DESC + SPORT_DESC + AMOUNT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_END_DATE));

        // combined duplicates
        assertParseFailure(parser,
                NAME_DESC + ORG_DESC + START_DESC + END_DESC
                        + NAME_DESC + ORG_DESC + START_DESC + END_DESC + SPORT_DESC + AMOUNT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(
                        PREFIX_NAME, PREFIX_ORG, PREFIX_START_DATE, PREFIX_END_DATE));
    }
}
