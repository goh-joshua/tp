package seedu.address.model.athlete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.athlete.TypicalAthletes.ALICE;
import static seedu.address.testutil.athlete.TypicalAthletes.BENSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.athlete.exceptions.AthleteNotFoundException;
import seedu.address.model.athlete.exceptions.DuplicateAthleteException;
import seedu.address.testutil.athlete.AthleteBuilder;

public class UniqueAthleteListTest {

    private final UniqueAthleteList uniqueAthleteList = new UniqueAthleteList();

    @Test
    public void contains_nullAthlete_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAthleteList.contains(null));
    }

    @Test
    public void contains_athleteNotInList_returnsFalse() {
        assertFalse(uniqueAthleteList.contains(ALICE));
    }

    @Test
    public void contains_athleteInList_returnsTrue() {
        uniqueAthleteList.add(ALICE);
        assertTrue(uniqueAthleteList.contains(ALICE));
    }

    @Test
    public void contains_athleteWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAthleteList.add(ALICE);
        Athlete editedAlice = new AthleteBuilder(ALICE).build();
        assertTrue(uniqueAthleteList.contains(editedAlice));
    }

    @Test
    public void add_nullAthlete_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAthleteList.add(null));
    }

    @Test
    public void add_duplicateAthlete_throwsDuplicatePersonException() {
        uniqueAthleteList.add(ALICE);
        assertThrows(DuplicateAthleteException.class, () -> uniqueAthleteList.add(ALICE));
    }

    @Test
    public void remove_nullAthlete_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAthleteList.remove(null));
    }

    @Test
    public void remove_athleteDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(AthleteNotFoundException.class, () -> uniqueAthleteList.remove(ALICE));
    }

    @Test
    public void remove_existingAthlete_removesAthlete() {
        uniqueAthleteList.add(ALICE);
        uniqueAthleteList.remove(ALICE);
        UniqueAthleteList expectedUniqueAthleteList = new UniqueAthleteList();
        assertEquals(expectedUniqueAthleteList, uniqueAthleteList);
    }

    @Test
    public void setAthletes_nullUniqueAthleteList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAthleteList.setAthletes((UniqueAthleteList) null));
    }

    @Test
    public void setAthletes_uniqueAthleteList_replacesOwnListWithProvidedUniqueAthleteList() {
        uniqueAthleteList.add(ALICE);
        UniqueAthleteList expectedUniqueAthleteList = new UniqueAthleteList();
        expectedUniqueAthleteList.add(BENSON);
        uniqueAthleteList.setAthletes(expectedUniqueAthleteList);
        assertEquals(expectedUniqueAthleteList, uniqueAthleteList);
    }

    @Test
    public void setAthletes_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAthleteList.setAthletes((List<Athlete>) null));
    }

    @Test
    public void setAthletes_list_replacesOwnListWithProvidedList() {
        uniqueAthleteList.add(ALICE);
        List<Athlete> athleteList = Collections.singletonList(BENSON);
        uniqueAthleteList.setAthletes(athleteList);
        UniqueAthleteList expectedUniqueAthleteList = new UniqueAthleteList();
        expectedUniqueAthleteList.add(BENSON);
        assertEquals(expectedUniqueAthleteList, uniqueAthleteList);
    }

    @Test
    public void setAthletes_listWithDuplicateAthletes_throwsDuplicateAthleteException() {
        List<Athlete> listWithDuplicateAthletes = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateAthleteException.class, () -> uniqueAthleteList.setAthletes(listWithDuplicateAthletes));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueAthleteList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueAthleteList.asUnmodifiableObservableList().toString(), uniqueAthleteList.toString());
    }
}
