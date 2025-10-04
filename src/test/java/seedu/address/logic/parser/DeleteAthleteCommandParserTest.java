package seedu.address.logic.parser;

import static seedu.address.logic.AthleteMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.AthleteCommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteAthleteCommand;

public class DeleteAthleteCommandParserTest {

    private DeleteAthleteCommandParser parser = new DeleteAthleteCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // invalid input: missing prefixes or wrong format
        String invalidInput = "Alice Basketball";
        assertParseFailure(parser, invalidInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAthleteCommand.MESSAGE_USAGE));
    }
}
