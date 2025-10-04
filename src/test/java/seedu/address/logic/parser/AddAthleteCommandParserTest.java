package seedu.address.logic.parser;

import static seedu.address.logic.AthleteMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AthleteCommandTestUtil.AGE_DESC_AMY;
import static seedu.address.logic.commands.AthleteCommandTestUtil.AGE_DESC_BOB;
import static seedu.address.logic.commands.AthleteCommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.AthleteCommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.AthleteCommandTestUtil.INVALID_AGE_DESC;
import static seedu.address.logic.commands.AthleteCommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.AthleteCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.AthleteCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.AthleteCommandTestUtil.INVALID_SPORT_DESC;
import static seedu.address.logic.commands.AthleteCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.AthleteCommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.AthleteCommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.AthleteCommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.AthleteCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.AthleteCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.AthleteCommandTestUtil.SPORT_DESC_AMY;
import static seedu.address.logic.commands.AthleteCommandTestUtil.SPORT_DESC_BOB;
import static seedu.address.logic.commands.AthleteCommandTestUtil.VALID_AGE_AMY;
import static seedu.address.logic.commands.AthleteCommandTestUtil.VALID_AGE_BOB;
import static seedu.address.logic.commands.AthleteCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.AthleteCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.AthleteCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.AthleteCommandTestUtil.VALID_SPORT_AMY;
import static seedu.address.logic.commands.AthleteCommandTestUtil.VALID_SPORT_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.AthleteCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.AthleteCommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAthletes.ALICE;
import static seedu.address.testutil.TypicalAthletes.BENSON;
import static seedu.address.logic.AthleteMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPORT;
import static seedu.address.logic.parser.AthleteCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.AthleteCommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAthletes.ALICE;
import static seedu.address.testutil.TypicalAthletes.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.AthleteMessages;
import seedu.address.logic.commands.AddAthleteCommand;
import seedu.address.model.athlete.Age;
import seedu.address.model.athlete.Email;
import seedu.address.model.athlete.Name;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.Phone;
import seedu.address.model.athlete.Sport;
import seedu.address.testutil.AthleteBuilder;

public class AddAthleteCommandParserTest {
    private AddAthleteCommandParser parser = new AddAthleteCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Athlete expectedAthlete = new AthleteBuilder(BENSON).build();

        assertParseSuccess(parser,  NAME_DESC_BOB + SPORT_DESC_BOB
                + AGE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                , new AddAthleteCommand(expectedAthlete));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAthleteCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + SPORT_DESC_BOB
                + AGE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB ,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + SPORT_DESC_BOB
                + AGE_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + SPORT_DESC_BOB
                        + AGE_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB,
                expectedMessage);

        // missing sport prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_SPORT_BOB
                        + AGE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // missing age prefix
        assertParseFailure(parser, NAME_DESC_BOB + SPORT_DESC_BOB
                        + VALID_AGE_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_SPORT_BOB
                        + VALID_AGE_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + SPORT_DESC_BOB
                + AGE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + SPORT_DESC_BOB
                + AGE_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + SPORT_DESC_BOB
                + AGE_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);

        // invalid sport
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_SPORT_DESC
                + AGE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB, Sport.MESSAGE_CONSTRAINTS);


        // invalid age
        assertParseFailure(parser, NAME_DESC_BOB + SPORT_DESC_BOB
                + INVALID_AGE_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB, Age.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + SPORT_DESC_BOB
                        + AGE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAthleteCommand.MESSAGE_USAGE));
    }
}
