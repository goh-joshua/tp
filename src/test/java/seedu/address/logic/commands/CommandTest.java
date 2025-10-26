package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Unit tests for the abstract {@link Command} class using a dummy subclass.
 */
public class CommandTest {

    /**
     * Dummy command subclass for testing purposes.
     */
    private static class DummyCommand extends Command {
        private final String message;
        private final boolean throwException;

        DummyCommand(String message, boolean throwException) {
            this.message = message;
            this.throwException = throwException;
        }

        @Override
        public CommandResult execute(Model model) throws CommandException {
            if (throwException) {
                throw new CommandException("Error occurred");
            }
            return new CommandResult(message);
        }
    }

    @Test
    public void execute_returnsCorrectMessage() throws Exception {
        Command command = new DummyCommand("Success!", false);
        CommandResult result = command.execute(null); // model can be null for testing
        assertEquals("Success!", result.getFeedbackToUser());
    }

    @Test
    public void execute_throwsCommandException() {
        Command command = new DummyCommand("Fail!", true);

        assertThrows(CommandException.class, () -> command.execute(null));
    }
}
