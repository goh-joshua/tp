package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.AthleteModel.PREDICATE_SHOW_ALL_ATHLETES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAthletes.ALICE;
import static seedu.address.testutil.TypicalAthletes.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.athlete.NameContainsKeywordsPredicate;
import seedu.address.testutil.AthleteBookBuilder;

public class AthleteModelManagerTest {

    private AthleteModelManager modelManager = new AthleteModelManager();

    @Test
    public void constructor() {
        assertEquals(new AthleteUserPrefs(), modelManager.getAthleteUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AthleteBook(), new AthleteBook(modelManager.getAthleteBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAthleteUserPrefs(null));
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setFilePath(null));
    }

    @Test
    public void setFilePath_validPath_setsFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setFilePath(path);
        assertEquals(path, modelManager.getFilePath());
    }

    @Test
    public void hasAthlete_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasAthlete(null));
    }

    @Test
    public void hasAthlete_athleteNotInAthleteBook_returnsFalse() {
        assertFalse(modelManager.hasAthlete(ALICE));
    }

    @Test
    public void hasAthlete_athleteInAthleteBook_returnsTrue() {
        modelManager.addAthlete(ALICE);
        assertTrue(modelManager.hasAthlete(ALICE));
    }

    @Test
    public void getFilteredAthleteList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredAthleteList().remove(0));
    }

    @Test
    public void equals() {
        AthleteBook athleteBook = new AthleteBookBuilder().withAthlete(ALICE).withAthlete(BENSON).build();
        AthleteBook differentAthleteBook = new AthleteBook();
        AthleteUserPrefs userPrefs = new AthleteUserPrefs();

        // same values -> returns true
        modelManager = new AthleteModelManager(athleteBook, userPrefs);
        AthleteModelManager modelManagerCopy = new AthleteModelManager(athleteBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new AthleteModelManager(differentAthleteBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredAthleteList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new AthleteModelManager(athleteBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredAthleteList(PREDICATE_SHOW_ALL_ATHLETES);

        // different userPrefs -> returns false
        AthleteUserPrefs differentUserPrefs = new AthleteUserPrefs();
        differentUserPrefs.setFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new AthleteModelManager(athleteBook, differentUserPrefs)));
    }
}
