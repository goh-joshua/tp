package seedu.address.logic.parser.athlete;

import static seedu.address.logic.AthleteMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.athlete.AthleteCommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.athlete.DeleteAthleteCommand;

/**
 * Tests for {@link DeleteAthleteCommandParser}.
 */
public class DeleteAthleteCommandParserTest {

    private final DeleteAthleteCommandParser parser = new DeleteAthleteCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String invalidInput = "Alice Basketball";
        assertParseFailure(parser, invalidInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAthleteCommand.MESSAGE_USAGE));
    }
}
