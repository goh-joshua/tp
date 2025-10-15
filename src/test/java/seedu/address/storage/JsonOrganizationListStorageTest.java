package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrganizations.NIKE;
import static seedu.address.testutil.TypicalOrganizations.getTypicalOrganizations;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.organization.Organization;
import seedu.address.model.organization.OrganizationList;
import seedu.address.model.organization.ReadOnlyOrganizationList;
import seedu.address.testutil.OrganizationBuilder;

/**
 * Tests for {@link JsonOrganizationListStorage}.
 */
public class JsonOrganizationListStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonOrganizationListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readOrganizationList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readOrganizationList(null));
    }

    private Optional<ReadOnlyOrganizationList> readOrganizationList(String filePath) throws Exception {
        return new JsonOrganizationListStorage(Paths.get(filePath))
                .readOrganizationList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readOrganizationList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () ->
                readOrganizationList("notJsonFormatOrganizationList.json"));
    }

    @Test
    public void readOrganizationList_invalidOrganizationOrganizationList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () ->
                readOrganizationList("invalidOrganizationOrganizationList.json"));
    }

    @Test
    public void readOrganizationList_invalidAndValidOrganizationOrganizationList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readOrganizationList(
                "invalidAndValidOrganizationOrganizationList.json"));
    }

    @Test
    public void readAndSaveOrganizationList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempOrganizationList.json");
        OrganizationList original = new OrganizationList();
        original.setOrganizations(getTypicalOrganizations());
        JsonOrganizationListStorage jsonOrganizationListStorage = new JsonOrganizationListStorage(filePath);

        // Save in new file and read back
        jsonOrganizationListStorage.saveOrganizationList(original, filePath);
        ReadOnlyOrganizationList readBack = jsonOrganizationListStorage.readOrganizationList(filePath).get();
        assertEquals(original.getOrganizationList(), readBack.getOrganizationList());

        // Modify data, overwrite existing file, and read back
        Organization puma = new OrganizationBuilder().withName("Puma")
                .withPhone("93456789")
                .withEmail("michael.brown@puma.com").build();
        original.addOrganization(puma);
        original.removeOrganization(NIKE);
        jsonOrganizationListStorage.saveOrganizationList(original, filePath);
        readBack = jsonOrganizationListStorage.readOrganizationList(filePath).get();
        assertEquals(original.getOrganizationList(), readBack.getOrganizationList());

        // Save and read without specifying file path
        Organization underArmour = new OrganizationBuilder().withName("Under Armour")
                .withPhone("98765432")
                .withEmail("sarah.johnson@ua.com").build();
        original.addOrganization(underArmour);
        jsonOrganizationListStorage.saveOrganizationList(original); // file path not specified
        readBack = jsonOrganizationListStorage.readOrganizationList().get(); // file path not specified
        assertEquals(original.getOrganizationList(), readBack.getOrganizationList());
    }

    @Test
    public void saveOrganizationList_nullOrganizationList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveOrganizationList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code organizationList} at the specified {@code filePath}.
     */
    private void saveOrganizationList(ReadOnlyOrganizationList organizationList, String filePath) {
        try {
            new JsonOrganizationListStorage(Paths.get(filePath))
                    .saveOrganizationList(organizationList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveOrganizationList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveOrganizationList(new OrganizationList(), null));
    }
}
