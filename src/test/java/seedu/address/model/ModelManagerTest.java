package seedu.address.model;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.contract.Contract;
import seedu.address.model.organization.Organization;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.OrganizationBuilder;
import seedu.address.testutil.athlete.AthleteBuilder;
import seedu.address.testutil.contract.ContractBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    // ============================================================
    // Existing person-centric tests
    // ============================================================

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
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
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void equals_personScenarios() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }

    // ============================================================
    // Athlete tests
    // ============================================================

    @Test
    public void hasAthlete_nullAthlete_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasAthlete(null));
    }

    @Test
    public void hasAthlete_athleteNotInAddressBook_returnsFalse() {
        Athlete athlete = new AthleteBuilder().withName("Alice Pauline").build();
        assertFalse(modelManager.hasAthlete(athlete));
    }

    @Test
    public void hasAthlete_athleteInAddressBook_returnsTrue() {
        Athlete athlete = new AthleteBuilder().withName("Alice Pauline").build();
        modelManager.addAthlete(athlete);
        assertTrue(modelManager.hasAthlete(athlete));
    }

    @Test
    public void getFilteredAthleteList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredAthleteList().remove(0));
    }

    // ============================================================
    // Contract tests
    // ============================================================

    @Test
    public void hasContract_nullContract_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasContract(null));
    }

    @Test
    public void hasContract_contractNotInAddressBook_returnsFalse() {
        Contract contract = new ContractBuilder().build();
        assertFalse(modelManager.hasContract(contract));
    }

    @Test
    public void hasContract_contractInAddressBook_returnsTrue() {
        Contract contract = new ContractBuilder().build();
        modelManager.addContract(contract);
        assertTrue(modelManager.hasContract(contract));
    }

    @Test
    public void getFilteredContractList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredContractList().remove(0));
    }

    // ============================================================
    // Organization tests (currently unsupported ops)
    // ============================================================

    @Test
    public void organization_methods_throwUnsupportedOperationException() {
        Organization org = new OrganizationBuilder().withName("Nike").build();

        assertThrows(UnsupportedOperationException.class, () -> modelManager.hasOrganization(org));
        assertThrows(UnsupportedOperationException.class, () -> modelManager.addOrganization(org));
        assertThrows(UnsupportedOperationException.class, () -> modelManager.deleteOrganization(org));
        assertThrows(UnsupportedOperationException.class, () -> modelManager.setOrganization(org, org));
        assertThrows(UnsupportedOperationException.class, () -> modelManager.updateFilteredOrganizationList(o -> true));
    }

    @Test
    public void getFilteredOrganizationList_returnsNotNullInitiallyEmpty() {
        ObservableList<Organization> orgs = modelManager.getFilteredOrganizationList();
        assertNotNull(orgs);
        assertTrue(orgs.isEmpty()); // placeholder list in current implementation
    }

    // ============================================================
    // Helper (optionally used; here just to show typing)
    // ============================================================

    private <T> void setPredicate(ObservableList<T> list, Predicate<T> predicate) {
        // no-op helper to avoid unused warnings if needed
    }
}
