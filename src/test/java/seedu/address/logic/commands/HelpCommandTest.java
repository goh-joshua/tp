package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;

/**
 * Unit tests for {@link HelpCommand}.
 */
public class HelpCommandTest {

    @Test
    public void execute_helpCommand_returnsExpectedCommandResult() {
        HelpCommand helpCommand = new HelpCommand();

        // HelpCommand does not depend on the Model, so null is fine
        CommandResult result = helpCommand.execute((Model) null);

        // Check message
        assertEquals(HelpCommand.SHOWING_HELP_MESSAGE, result.getFeedbackToUser());

        // Check flags
        assertTrue(result.isShowHelp());
        assertFalse(result.isExit());
    }
}
