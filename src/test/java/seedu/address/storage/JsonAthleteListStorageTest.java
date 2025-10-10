package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.athlete.TypicalAthletes.ALICE;
import static seedu.address.testutil.athlete.TypicalAthletes.BENSON;
import static seedu.address.testutil.athlete.TypicalAthletes.CARL;
import static seedu.address.testutil.athlete.TypicalAthletes.getTypicalAthletes;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.athlete.AthleteList;
import seedu.address.model.athlete.ReadOnlyAthleteList;

public class JsonAthleteListStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonAthleteListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAthleteList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAthleteList(null));
    }

    private Optional<ReadOnlyAthleteList> readAthleteList(String filePath) throws Exception {
        return new JsonAthleteListStorage(Paths.get(filePath))
                .readAthleteList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAthleteList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readAthleteList("notJsonFormatAthleteList.json"));
    }

    @Test
    public void readAthleteList_invalidAthleteAthleteList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAthleteList("invalidAthleteAthleteList.json"));
    }

    @Test
    public void readAthleteList_invalidAndValidAthleteAthleteList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAthleteList(
                "invalidAndValidAthleteAthleteList.json"));
    }

    @Test
    public void readAndSaveAthleteList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAthleteList.json");
        AthleteList original = new AthleteList();
        original.setAthletes(getTypicalAthletes());
        JsonAthleteListStorage jsonAthleteListStorage = new JsonAthleteListStorage(filePath);

        // Save in new file and read back
        jsonAthleteListStorage.saveAthleteList(original, filePath);
        ReadOnlyAthleteList readBack = jsonAthleteListStorage.readAthleteList(filePath).get();
        assertEquals(original, new AthleteList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addAthlete(ALICE);
        original.removeAthlete(BENSON);
        jsonAthleteListStorage.saveAthleteList(original, filePath);
        readBack = jsonAthleteListStorage.readAthleteList(filePath).get();
        assertEquals(original, new AthleteList(readBack));

        // Save and read without specifying file path
        original.addAthlete(CARL);
        jsonAthleteListStorage.saveAthleteList(original); // file path not specified
        readBack = jsonAthleteListStorage.readAthleteList().get(); // file path not specified
        assertEquals(original, new AthleteList(readBack));
    }

    @Test
    public void saveAthleteList_nullAthleteList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAthleteList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code athleteList} at the specified {@code filePath}.
     */
    private void saveAthleteList(ReadOnlyAthleteList athleteList, String filePath) {
        try {
            new JsonAthleteListStorage(Paths.get(filePath))
                    .saveAthleteList(athleteList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAthleteList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAthleteList(new AthleteList(), null));
    }
}
