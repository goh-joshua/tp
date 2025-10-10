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
import seedu.address.logic.commands.contract.AddContractCommand;
import seedu.address.model.athlete.Name;
import seedu.address.model.athlete.Sport;
import seedu.address.model.contract.Amount;
import seedu.address.model.contract.Date8;
import seedu.address.model.organization.OrganizationName;

/**
 * Tests for {@link AddContractCommandParser}.
 */
public class AddContractCommandParserTest {
    private static final String VALID_ATHLETE_NAME = "Lionel Messi";
    private static final String VALID_SPORT = "Football";
    private static final String VALID_ORG_NAME = "Inter Miami CF";
    private static final String VALID_START = "01012024";
    private static final String VALID_END = "01012025";
    private static final String VALID_AMOUNT = "5000000";

    private static final String NAME_DESC = " " + PREFIX_NAME + VALID_ATHLETE_NAME;
    private static final String SPORT_DESC = " " + PREFIX_SPORT + VALID_SPORT;
    private static final String ORG_DESC = " " + PREFIX_ORG + VALID_ORG_NAME;
    private static final String START_DESC = " " + PREFIX_START_DATE + VALID_START;
    private static final String END_DESC = " " + PREFIX_END_DATE + VALID_END;
    private static final String AMT_DESC = " " + PREFIX_AMOUNT + VALID_AMOUNT;

    private static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "1234"; // Name with digits
    private static final String INVALID_SPORT_DESC = " " + PREFIX_SPORT + ""; // empty
    private static final String INVALID_ORG_DESC = " " + PREFIX_ORG + "@@"; // invalid symbols
    private static final String INVALID_START_DESC =
            " " + PREFIX_START_DATE + "31132024"; // invalid date
    private static final String INVALID_END_DESC =
            " " + PREFIX_END_DATE + "abcd2024"; // non-digit
    private static final String INVALID_AMT_DESC =
            " " + PREFIX_AMOUNT + "-10"; // negative

    private final AddContractCommandParser parser = new AddContractCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        AddContractCommand expected = new AddContractCommand(
                new Name(VALID_ATHLETE_NAME),
                new Sport(VALID_SPORT),
                new OrganizationName(VALID_ORG_NAME),
                new Date8(VALID_START),
                new Date8(VALID_END),
                new Amount(Integer.parseInt(VALID_AMOUNT))
        );

        assertParseSuccess(parser, NAME_DESC + SPORT_DESC + ORG_DESC + START_DESC + END_DESC + AMT_DESC, expected);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContractCommand.MESSAGE_USAGE);

        assertParseFailure(parser, SPORT_DESC + ORG_DESC + START_DESC + END_DESC + AMT_DESC, expectedMessage);
        assertParseFailure(parser, NAME_DESC + ORG_DESC + START_DESC + END_DESC + AMT_DESC, expectedMessage);
        assertParseFailure(parser, NAME_DESC + SPORT_DESC + START_DESC + END_DESC + AMT_DESC, expectedMessage);
        assertParseFailure(parser, NAME_DESC + SPORT_DESC + ORG_DESC + END_DESC + AMT_DESC, expectedMessage);
        assertParseFailure(parser, NAME_DESC + SPORT_DESC + ORG_DESC + START_DESC + AMT_DESC, expectedMessage);
        assertParseFailure(parser, NAME_DESC + SPORT_DESC + ORG_DESC + START_DESC + END_DESC, expectedMessage);
        assertParseFailure(parser, "preamble" + NAME_DESC + SPORT_DESC + ORG_DESC + START_DESC + END_DESC + AMT_DESC,
                expectedMessage);
    }

    @Test
    public void parse_invalidValues_failure() {
        assertParseFailure(parser, NAME_DESC + INVALID_SPORT_DESC + ORG_DESC + START_DESC + END_DESC + AMT_DESC,
                Sport.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, NAME_DESC + SPORT_DESC + INVALID_ORG_DESC + START_DESC + END_DESC + AMT_DESC,
                OrganizationName.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, NAME_DESC + SPORT_DESC + ORG_DESC + INVALID_START_DESC + END_DESC + AMT_DESC,
                Date8.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, NAME_DESC + SPORT_DESC + ORG_DESC + START_DESC + INVALID_END_DESC + AMT_DESC,
                Date8.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, NAME_DESC + SPORT_DESC + ORG_DESC + START_DESC + END_DESC + INVALID_AMT_DESC,
                Amount.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                INVALID_NAME_DESC + INVALID_SPORT_DESC + INVALID_ORG_DESC + INVALID_START_DESC
                        + INVALID_END_DESC + INVALID_AMT_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_duplicatePrefixes_failure() {
        assertParseFailure(parser,
                NAME_DESC + NAME_DESC + SPORT_DESC + ORG_DESC + START_DESC + END_DESC + AMT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        assertParseFailure(parser,
                NAME_DESC + SPORT_DESC + SPORT_DESC + ORG_DESC + START_DESC + END_DESC + AMT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SPORT));

        assertParseFailure(parser,
                NAME_DESC + SPORT_DESC + ORG_DESC + ORG_DESC + START_DESC + END_DESC + AMT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ORG));

        assertParseFailure(parser,
                NAME_DESC + SPORT_DESC + ORG_DESC + START_DESC + START_DESC + END_DESC + AMT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_DATE));

        assertParseFailure(parser,
                NAME_DESC + SPORT_DESC + ORG_DESC + START_DESC + END_DESC + END_DESC + AMT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_END_DATE));

        assertParseFailure(parser,
                NAME_DESC + SPORT_DESC + ORG_DESC + START_DESC + END_DESC + AMT_DESC + AMT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AMOUNT));

        assertParseFailure(parser,
                NAME_DESC + SPORT_DESC + ORG_DESC + START_DESC + END_DESC + AMT_DESC
                        + NAME_DESC + SPORT_DESC + ORG_DESC + START_DESC + END_DESC + AMT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(
                        PREFIX_NAME, PREFIX_SPORT, PREFIX_ORG, PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_AMOUNT));
    }
}
