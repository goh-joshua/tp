package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAthletes.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.AthleteMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AthleteBook;
import seedu.address.model.AthleteModel;
import seedu.address.model.ReadOnlyAthleteBook;
import seedu.address.model.ReadOnlyAthleteUserPrefs;
import seedu.address.model.athlete.Athlete;
import seedu.address.testutil.AthleteBuilder;

public class AddAthleteCommandTest {

    @Test
    public void constructor_nullAthlete_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAthleteCommand(null));
    }

    @Test
    public void execute_athleteAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAthleteAdded modelStub = new ModelStubAcceptingAthleteAdded();
        Athlete validAthlete = new AthleteBuilder().build();

        CommandResult commandResult = new AddAthleteCommand(validAthlete).execute(modelStub);

        assertEquals(String.format(AddAthleteCommand.MESSAGE_SUCCESS, AthleteMessages.format(validAthlete)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAthlete), modelStub.athleteAdded);
    }

    @Test
    public void execute_duplicateAthlete_throwsCommandException() {
        Athlete validAthlete = new AthleteBuilder().build();
        AddAthleteCommand addAthleteCommand = new AddAthleteCommand(validAthlete);
        ModelStub modelStub = new ModelStubWithAthlete(validAthlete);

        assertThrows(CommandException.class,
                AddAthleteCommand.MESSAGE_DUPLICATE_ATHLETE, () -> addAthleteCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Athlete alice = new AthleteBuilder().withName("Alice").build();
        Athlete bob = new AthleteBuilder().withName("Bob").build();
        AddAthleteCommand addAliceCommand = new AddAthleteCommand(alice);
        AddAthleteCommand addBobCommand = new AddAthleteCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddAthleteCommand addAliceCommandCopy = new AddAthleteCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddAthleteCommand addAthleteCommand = new AddAthleteCommand(ALICE);
        String expected = AddAthleteCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addAthleteCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements AthleteModel {
        @Override
        public void setAthleteUserPrefs(ReadOnlyAthleteUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAthleteUserPrefs getAthleteUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAthlete(Athlete athlete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAthleteBook(ReadOnlyAthleteBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAthleteBook getAthleteBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAthlete(Athlete athlete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAthlete(Athlete target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Athlete> getFilteredAthleteList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAthleteList(Predicate<Athlete> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single athlete.
     */
    private class ModelStubWithAthlete extends ModelStub {
        private final Athlete athlete;

        ModelStubWithAthlete(Athlete athlete) {
            requireNonNull(athlete);
            this.athlete = athlete;
        }

        @Override
        public boolean hasAthlete(Athlete athlete) {
            requireNonNull(athlete);
            return this.athlete.isSameAthlete(athlete);
        }
    }

    /**
     * A Model stub that always accept the athlete being added.
     */
    private class ModelStubAcceptingAthleteAdded extends ModelStub {
        final ArrayList<Athlete> athleteAdded = new ArrayList<>();

        @Override
        public boolean hasAthlete(Athlete athlete) {
            requireNonNull(athlete);
            return athleteAdded.stream().anyMatch(athlete::isSameAthlete);
        }

        @Override
        public void addAthlete(Athlete athlete) {
            requireNonNull(athlete);
            athleteAdded.add(athlete);
        }

        @Override
        public ReadOnlyAthleteBook getAthleteBook() {
            return new AthleteBook();
        }
    }

}
