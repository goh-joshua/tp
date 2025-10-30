package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.RefreshCommand;
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
     * The input is first split into a command word and arguments, then the appropriate
     * parser is invoked based on the command word.
     *
     * @param userInput Full user input string. Cannot be null.
     * @return The command based on the user input.
     * @throws ParseException If the user input does not conform to the expected format
     *                        or if the command word is not recognized.
     */
    public Command parseCommand(String userInput) throws ParseException {
        assert userInput != null : "User input should not be null";
        logger.fine("Parsing command: " + userInput);

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            logger.warning("Invalid command format: " + userInput);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord").toLowerCase();
        final String arguments = matcher.group("arguments");
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        Command result = parseCommandWord(commandWord, arguments);
        assert result != null : "Parsed command should not be null";
        logger.info("Successfully parsed command: " + commandWord);
        return result;
    }

    /**
     * Parses the command word and arguments to create the appropriate Command object.
     *
     * @param commandWord The command word in lowercase.
     * @param arguments The arguments string.
     * @return The parsed Command object.
     * @throws ParseException If the command word is not recognized or arguments are invalid.
     */
    private Command parseCommandWord(String commandWord, String arguments) throws ParseException {
        logger.fine("Parsing command word: \"" + commandWord + "\" with arguments: \"" + arguments + "\"");
        switch (commandWord) {
        case AddOrganizationCommand.COMMAND_WORD:
            return new AddOrganizationCommandParser().parse(arguments);
        case DeleteOrganizationCommand.COMMAND_WORD:
            return new DeleteOrganizationCommandParser().parse(arguments);
        case AddAthleteCommand.COMMAND_WORD:
            return new AddAthleteCommandParser().parse(arguments);
        case DeleteAthleteCommand.COMMAND_WORD:
            return new DeleteAthleteCommandParser().parse(arguments);
        case AddContractCommand.COMMAND_WORD:
            return new AddContractCommandParser().parse(arguments);
        case DeleteContractCommand.COMMAND_WORD:
            return new DeleteContractCommandParser().parse(arguments);
        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case RefreshCommand.COMMAND_WORD:
            return new RefreshCommand();
        default:
            logger.finer("This user input caused a ParseException: " + commandWord);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
