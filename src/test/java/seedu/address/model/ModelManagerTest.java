package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.contract.Contract;
import seedu.address.model.organization.Organization;
import seedu.address.testutil.OrganizationBuilder;
import seedu.address.testutil.athlete.AthleteBuilder;
import seedu.address.testutil.contract.ContractBuilder;

/**
 * Tests for {@link ModelManager}.
 */
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
        assertThrows(UnsupportedOperationException.class, () ->
                modelManager.getFilteredAthleteList().remove(0));
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
        assertThrows(UnsupportedOperationException.class, () ->
                modelManager.getFilteredContractList().remove(0));
    }

    // ============================================================
    // Organization tests
    // ============================================================

    @Test
    public void organization_methods_workCorrectly() {
        Organization org = new OrganizationBuilder().withName("Nike").build();

        // Test that organization methods work as expected
        assertFalse(modelManager.hasOrganization(org));
        modelManager.addOrganization(org);
        assertTrue(modelManager.hasOrganization(org));

        // Test filtering
        modelManager.updateFilteredOrganizationList(o -> true);
        assertEquals(1, modelManager.getFilteredOrganizationList().size());

        // Test deletion
        modelManager.deleteOrganization(org);
        assertFalse(modelManager.hasOrganization(org));
    }

    @Test
    public void getFilteredOrganizationList_returnsNotNullInitiallyEmpty() {
        ObservableList<Organization> orgs = modelManager.getFilteredOrganizationList();
        assertNotNull(orgs);
        assertTrue(orgs.isEmpty());
    }

    // ============================================================
    // Helper
    // ============================================================

    private <T> void setPredicate(ObservableList<T> list, Predicate<T> predicate) {
        // no-op
    }
}
