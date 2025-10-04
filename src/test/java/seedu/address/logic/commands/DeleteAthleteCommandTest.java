package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AthleteCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.AthleteCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAthletes.getTypicalAthleteBook;
import static seedu.address.testutil.TypicalAthletes.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.AthleteMessages;
import seedu.address.model.AthleteModel;
import seedu.address.model.AthleteModelManager;
import seedu.address.model.AthleteUserPrefs;
import seedu.address.model.athlete.Name;
import seedu.address.model.athlete.Sport;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteAthleteCommand}.
 */
public class DeleteAthleteCommandTest {

    private AthleteModel model = new AthleteModelManager(getTypicalAthleteBook(), new AthleteUserPrefs());

    @Test
    public void execute_validNameSportUnfilteredList_success() {
        Name nameToDelete = ALICE.getName();
        Sport sportToDelete = ALICE.getSport();

        DeleteAthleteCommand deleteCommand = new DeleteAthleteCommand(nameToDelete, sportToDelete);

        String expectedMessage = String.format(DeleteAthleteCommand.MESSAGE_DELETE_ATHLETE_SUCCESS,
                ALICE);

        AthleteModel expectedModel = new AthleteModelManager(model.getAthleteBook(), new AthleteUserPrefs());
        expectedModel.deleteAthlete(ALICE);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameSportUnfilteredList_throwsCommandException() {
        Name invalidName = new Name("Nonexistent Athlete");
        Sport invalidSport = new Sport("Nonexistent Sport");

        DeleteAthleteCommand deleteCommand = new DeleteAthleteCommand(invalidName, invalidSport);

        assertCommandFailure(deleteCommand, model, AthleteMessages.MESSAGE_INVALID_ATHLETE);
    }

    @Test
    public void equals() {
        DeleteAthleteCommand deleteFirstCommand = new DeleteAthleteCommand(ALICE.getName(), ALICE.getSport());
        DeleteAthleteCommand deleteSecondCommand = new DeleteAthleteCommand(
                new Name("Bob"), new Sport("Basketball"));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteAthleteCommand deleteFirstCommandCopy =
                new DeleteAthleteCommand(ALICE.getName(), ALICE.getSport());
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different athlete -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        DeleteAthleteCommand deleteCommand = new DeleteAthleteCommand(ALICE.getName(), ALICE.getSport());
        String expected = DeleteAthleteCommand.class.getCanonicalName()
                + "{name=" + ALICE.getName()
                + ", sport=" + ALICE.getSport() + "}";
        assertEquals(expected, deleteCommand.toString());
    }
}
