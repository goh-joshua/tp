package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPORT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAthleteCommand;
import seedu.address.logic.commands.DeleteAthleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.Name;
import seedu.address.model.athlete.Sport;
import seedu.address.testutil.AthleteBuilder;
import seedu.address.testutil.AthleteUtil;

public class AthleteBookParserTest {

    private final AthleteBookParser parser = new AthleteBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Athlete athlete = new AthleteBuilder().build();
        AddAthleteCommand command = (AddAthleteCommand) parser.parseCommand(AthleteUtil.getAddAthleteCommand(athlete));
        assertEquals(new AddAthleteCommand(athlete), command);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        // Assume ALICE is a typical Athlete test object
        Name name = new Name("Alice");
        Sport sport = new Sport("Badminton");

        DeleteAthleteCommand command = (DeleteAthleteCommand) parser.parseCommand(
                DeleteAthleteCommand.COMMAND_WORD + " "
                        + PREFIX_NAME + name + " "
                        + PREFIX_SPORT + sport);

        assertEquals(new DeleteAthleteCommand(name, sport), command);
    }


    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, "Only adding and deleting of athlete profiles are allowed at this stage", ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
