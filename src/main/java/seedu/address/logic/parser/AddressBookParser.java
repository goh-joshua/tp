package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.athlete.AddAthleteCommand;
import seedu.address.logic.commands.athlete.DeleteAthleteCommand;
import seedu.address.logic.commands.contract.AddContractCommand;
import seedu.address.logic.commands.contract.DeleteContractCommand;
import seedu.address.logic.commands.organization.AddOrganizationCommand;
import seedu.address.logic.commands.organization.DeleteOrganizationCommand;
import seedu.address.logic.parser.athlete.AddAthleteCommandParser;
import seedu.address.logic.parser.athlete.DeleteAthleteCommandParser;
import seedu.address.logic.parser.contract.AddContractCommandParser;
import seedu.address.logic.parser.contract.DeleteContractCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.organization.AddOrganizationCommandParser;
import seedu.address.logic.parser.organization.DeleteOrganizationCommandParser;

/**
 * Parses user input.
 * Unified parser for AddressBook, Organizations, Athletes, and Contracts.
 */
public class AddressBookParser {

    /** Used for initial separation of command word and args. */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform to the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {
        // ================= People (core AB3) =================
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        // ================= Organizations =================
        case AddOrganizationCommand.COMMAND_WORD:
            return new AddOrganizationCommandParser().parse(arguments);

        case DeleteOrganizationCommand.COMMAND_WORD:
            return new DeleteOrganizationCommandParser().parse(arguments);

        // ================= Athletes =================
        case AddAthleteCommand.COMMAND_WORD:
            return new AddAthleteCommandParser().parse(arguments);

        case DeleteAthleteCommand.COMMAND_WORD:
            return new DeleteAthleteCommandParser().parse(arguments);

        // ================= Contracts =================
        case AddContractCommand.COMMAND_WORD:
            return new AddContractCommandParser().parse(arguments);

        case DeleteContractCommand.COMMAND_WORD:
            return new DeleteContractCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
