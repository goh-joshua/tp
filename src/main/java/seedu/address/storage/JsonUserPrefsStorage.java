package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * A class to access UserPrefs stored in the hard disk as a json file
 */
public class JsonUserPrefsStorage implements UserPrefsStorage {

    private Path filePath;

    /**
     * Constructs a {@code JsonUserPrefsStorage} with the specified file path.
     *
     * @param filePath The path to the JSON file.
     */
    public JsonUserPrefsStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the file path of the UserPrefs JSON file.
     *
     * @return the file path.
     */
    @Override
    public Path getUserPrefsFilePath() {
        return filePath;
    }

    /**
     * Reads the user preferences from the default JSON file path.
     *
     * @return An {@code Optional} containing the user preferences if the file exists.
     * @throws DataLoadingException if there were errors loading the data
     *                              (e.g., invalid JSON format).
     */
    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return readUserPrefs(filePath);
    }

    /**
     * Similar to {@link #readUserPrefs()}
     * @param prefsFilePath location of the data. Cannot be null.
     * @throws DataLoadingException if the file format is not as expected.
     */
    public Optional<UserPrefs> readUserPrefs(Path prefsFilePath) throws DataLoadingException {
        return JsonUtil.readJsonFile(prefsFilePath, UserPrefs.class);
    }

    /**
     * Saves the given user preferences to the default JSON file path.
     *
     * @param userPrefs The user preferences to save. Cannot be null.
     * @throws IOException if there was a problem writing to the file.
     */
    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        JsonUtil.saveJsonFile(userPrefs, filePath);
    }

}
