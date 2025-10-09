package seedu.address.logic.commands.athlete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPORT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.athlete.Athlete;

/**
 * Contains helper methods for testing athlete commands.
 */
public class AthleteCommandTestUtil {

    public static final String VALID_NAME_AMY = "Alice Pauline";
    public static final String VALID_NAME_BOB = "Benson Meier";
    public static final String VALID_PHONE_AMY = "94351253";
    public static final String VALID_PHONE_BOB = "90808767";
    public static final String VALID_EMAIL_AMY = "alice@example.com";
    public static final String VALID_EMAIL_BOB = "ben@example.com";
    public static final String VALID_SPORT_AMY = "Swimming";
    public static final String VALID_SPORT_BOB = "Pool";
    public static final String VALID_AGE_AMY = "30";
    public static final String VALID_AGE_BOB = "18";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String SPORT_DESC_AMY = " " + PREFIX_SPORT + VALID_SPORT_AMY;
    public static final String SPORT_DESC_BOB = " " + PREFIX_SPORT + VALID_SPORT_BOB;
    public static final String AGE_DESC_AMY = " " + PREFIX_AGE + VALID_AGE_AMY;
    public static final String AGE_DESC_BOB = " " + PREFIX_AGE + VALID_AGE_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_SPORT_DESC = " " + PREFIX_SPORT + "Basket++"; // '+' not allowed in sport
    public static final String INVALID_AGE_DESC = " " + PREFIX_AGE + "three"; // letters not allowed for age
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    /**
     * Executes the given {@code command}, confirms that:
     * <ul>
     *     <li>The returned {@link CommandResult} matches {@code expectedCommandResult}</li>
     *     <li>The {@code actualModel} matches {@code expectedModel}</li>
     * </ul>
     */
    public static void assertCommandSuccess(Command command, Model actualModel,
                                            CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel.getAddressBook(), actualModel.getAddressBook());
            assertEquals(expectedModel.getFilteredAthleteList(), actualModel.getFilteredAthleteList());
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that:
     * <ul>
     *     <li>A {@code CommandException} is thrown</li>
     *     <li>The CommandException message matches {@code expectedMessage}</li>
     *     <li>The address book and filtered athlete list in {@code actualModel} remain unchanged</li>
     * </ul>
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Athlete> expectedFilteredList = new ArrayList<>(actualModel.getFilteredAthleteList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredAthleteList());
    }
}
