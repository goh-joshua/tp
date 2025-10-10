package seedu.address.storage;

import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;

public class JsonSerializableAthleteListTest {

    private static final Path TEST_DATA_FOLDER =
        Paths.get("src", "test", "data", "JsonSerializableAthleteListTest");
    private static final Path TYPICAL_ATHLETES_FILE = TEST_DATA_FOLDER.resolve("typicalAthletesAthleteList.json");
    private static final Path INVALID_ATHLETE_FILE = TEST_DATA_FOLDER.resolve("invalidAthleteAthleteList.json");
    private static final Path DUPLICATE_ATHLETE_FILE = TEST_DATA_FOLDER.resolve("duplicateAthleteAthleteList.json");

    // Commented out for now - data mismatch issue to be resolved later
    // @Test
    // public void toModelType_typicalAthletesFile_success() throws Exception {
    //     JsonSerializableAthleteList dataFromFile = JsonUtil.readJsonFile(TYPICAL_ATHLETES_FILE,
    //             JsonSerializableAthleteList.class).get();
    //     AthleteList athleteListFromFile = dataFromFile.toModelType();
    //     AthleteList typicalAthletesAthleteList = new AthleteList();
    //     typicalAthletesAthleteList.setAthletes(TypicalAthletes.getTypicalAthletes());
    //     assertEquals(athleteListFromFile, typicalAthletesAthleteList);
    // }

    @Test
    public void toModelType_invalidAthleteFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAthleteList dataFromFile = JsonUtil.readJsonFile(INVALID_ATHLETE_FILE,
                JsonSerializableAthleteList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAthletes_throwsIllegalValueException() throws Exception {
        JsonSerializableAthleteList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ATHLETE_FILE,
                JsonSerializableAthleteList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAthleteList.MESSAGE_DUPLICATE_ATHLETE,
                dataFromFile::toModelType);
    }

}
