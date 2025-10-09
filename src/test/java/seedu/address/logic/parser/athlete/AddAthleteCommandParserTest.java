package seedu.address.logic.parser.athlete;

import static seedu.address.logic.AthleteMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.athlete.AthleteCommandTestUtil.AGE_DESC_BOB;
import static seedu.address.logic.commands.athlete.AthleteCommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.athlete.AthleteCommandTestUtil.INVALID_AGE_DESC;
import static seedu.address.logic.commands.athlete.AthleteCommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.athlete.AthleteCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.athlete.AthleteCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.athlete.AthleteCommandTestUtil.INVALID_SPORT_DESC;
import static seedu.address.logic.commands.athlete.AthleteCommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.athlete.AthleteCommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.athlete.AthleteCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.athlete.AthleteCommandTestUtil.SPORT_DESC_BOB;
import static seedu.address.logic.commands.athlete.AthleteCommandTestUtil.VALID_AGE_BOB;
import static seedu.address.logic.commands.athlete.AthleteCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.athlete.AthleteCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.athlete.AthleteCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.athlete.AthleteCommandTestUtil.VALID_SPORT_BOB;
import static seedu.address.logic.parser.athlete.AthleteCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.athlete.AthleteCommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.athlete.TypicalAthletes.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.athlete.AddAthleteCommand;
import seedu.address.model.athlete.Age;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.Email;
import seedu.address.model.athlete.Name;
import seedu.address.model.athlete.Phone;
import seedu.address.model.athlete.Sport;
import seedu.address.testutil.athlete.AthleteBuilder;

/**
 * Unit tests for {@link AddAthleteCommandParser}.
 */
public class AddAthleteCommandParserTest {

    private final AddAthleteCommandParser parser = new AddAthleteCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Athlete expectedAthlete = new AthleteBuilder(BENSON).build();

        assertParseSuccess(parser,
                NAME_DESC_BOB + SPORT_DESC_BOB + AGE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                new AddAthleteCommand(expectedAthlete));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAthleteCommand.MESSAGE_USAGE);

        assertParseFailure(parser,
                VALID_NAME_BOB + SPORT_DESC_BOB + AGE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        assertParseFailure(parser,
                NAME_DESC_BOB + SPORT_DESC_BOB + AGE_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        assertParseFailure(parser,
                NAME_DESC_BOB + SPORT_DESC_BOB + AGE_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB,
                expectedMessage);

        assertParseFailure(parser,
                NAME_DESC_BOB + VALID_SPORT_BOB + AGE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        assertParseFailure(parser,
                NAME_DESC_BOB + SPORT_DESC_BOB + VALID_AGE_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        assertParseFailure(parser,
                VALID_NAME_BOB + VALID_SPORT_BOB + VALID_AGE_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser,
                INVALID_NAME_DESC + SPORT_DESC_BOB + AGE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                NAME_DESC_BOB + SPORT_DESC_BOB + AGE_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                NAME_DESC_BOB + SPORT_DESC_BOB + AGE_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC,
                Email.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                NAME_DESC_BOB + INVALID_SPORT_DESC + AGE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                Sport.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                NAME_DESC_BOB + SPORT_DESC_BOB + INVALID_AGE_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                Age.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + NAME_DESC_BOB + SPORT_DESC_BOB + AGE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAthleteCommand.MESSAGE_USAGE));
    }
}
