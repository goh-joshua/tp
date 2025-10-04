package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAthletes.ALICE;
import static seedu.address.testutil.TypicalAthletes.getTypicalAthleteBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.exceptions.DuplicateAthleteException;
import seedu.address.testutil.AthleteBuilder;

public class AthleteBookTest {

    private final AthleteBook athleteBook = new AthleteBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), athleteBook.getAthleteList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> athleteBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAthleteBook_replacesData() {
        AthleteBook newData = getTypicalAthleteBook();
        athleteBook.resetData(newData);
        assertEquals(newData, athleteBook);
    }

    @Test
    public void resetData_withDuplicateAthletes_throwsDuplicateAthleteException() {
        // Two persons with the same identity fields
        Athlete editedAlice = new AthleteBuilder(ALICE)
                .build();
        List<Athlete> newPersons = Arrays.asList(ALICE, editedAlice);
        AthleteBookStub newData = new AthleteBookStub(newPersons);

        assertThrows(DuplicateAthleteException.class, () -> athleteBook.resetData(newData));
    }

    @Test
    public void hasAthlete_nullAthlete_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> athleteBook.hasAthlete(null));
    }

    @Test
    public void hasAthlete_athleteNotInAthleteBook_returnsFalse() {
        assertFalse(athleteBook.hasAthlete(ALICE));
    }

    @Test
    public void hasAthlete_athleteInAthleteBook_returnsTrue() {
        athleteBook.addAthlete(ALICE);
        assertTrue(athleteBook.hasAthlete(ALICE));
    }

    @Test
    public void hasAthlete_athleteWithSameIdentityFieldsInAthleteBook_returnsTrue() {
        athleteBook.addAthlete(ALICE);
        Athlete editedAlice = new AthleteBuilder(ALICE).build();
        assertTrue(athleteBook.hasAthlete(editedAlice));
    }

    @Test
    public void getAthleteList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> athleteBook.getAthleteList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AthleteBook.class.getCanonicalName() + "{athletes=" + athleteBook.getAthleteList() + "}";
        assertEquals(expected, athleteBook.toString());
    }

    /**
     * A stub ReadOnlyAthleteBook whose athletes list can violate interface constraints.
     */
    private static class AthleteBookStub implements ReadOnlyAthleteBook {
        private final ObservableList<Athlete> athletes = FXCollections.observableArrayList();

        AthleteBookStub(Collection<Athlete> athletes) {
            this.athletes.setAll(athletes);
        }

        @Override
        public ObservableList<Athlete> getAthleteList() {
            return athletes;
        }
    }

}
