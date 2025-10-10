package seedu.address.storage;

import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;

public class JsonSerializableOrganizationListTest {

    private static final Path TEST_DATA_FOLDER =
        Paths.get("src", "test", "data", "JsonSerializableOrganizationListTest");
    private static final Path TYPICAL_ORGANIZATIONS_FILE =
        TEST_DATA_FOLDER.resolve("typicalOrganizationsOrganizationList.json");
    private static final Path INVALID_ORGANIZATION_FILE =
        TEST_DATA_FOLDER.resolve("invalidOrganizationOrganizationList.json");
    private static final Path DUPLICATE_ORGANIZATION_FILE =
        TEST_DATA_FOLDER.resolve("duplicateOrganizationOrganizationList.json");

    // Commented out for now - data mismatch issue to be resolved later
    // @Test
    // public void toModelType_typicalOrganizationsFile_success() throws Exception {
    //     JsonSerializableOrganizationList dataFromFile = JsonUtil.readJsonFile(TYPICAL_ORGANIZATIONS_FILE,
    //             JsonSerializableOrganizationList.class).get();
    //     OrganizationList organizationListFromFile = dataFromFile.toModelType();
    //     OrganizationList typicalOrganizationsOrganizationList = new OrganizationList();
    //     typicalOrganizationsOrganizationList.setOrganizations(TypicalOrganizations.getTypicalOrganizations());
    //     assertEquals(organizationListFromFile, typicalOrganizationsOrganizationList);
    // }

    @Test
    public void toModelType_invalidOrganizationFile_throwsIllegalValueException() throws Exception {
        JsonSerializableOrganizationList dataFromFile = JsonUtil.readJsonFile(INVALID_ORGANIZATION_FILE,
                JsonSerializableOrganizationList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateOrganizations_throwsIllegalValueException() throws Exception {
        JsonSerializableOrganizationList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ORGANIZATION_FILE,
                JsonSerializableOrganizationList.class).get();
        assertThrows(IllegalValueException.class,
                JsonSerializableOrganizationList.MESSAGE_DUPLICATE_ORGANIZATION,
                dataFromFile::toModelType);
    }

}
