package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;

/**
 * Unit tests for {@link ExitCommand}.
 */
public class ExitCommandTest {

    @Test
    public void execute_exitCommand_returnsExpectedCommandResult() {
        ExitCommand exitCommand = new ExitCommand();

        // You can pass null because ExitCommand doesn't use the model
        CommandResult result = exitCommand.execute((Model) null);

        // Check message
        assertEquals(ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT, result.getFeedbackToUser());

        // Check flags
        assertFalse(result.isShowHelp());
        assertTrue(result.isExit());
    }
}
