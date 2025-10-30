package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.athlete.ReadOnlyAthleteList;

/**
 * A class to access athlete list data stored as a json file on the hard disk.
 */
public class JsonAthleteListStorage implements AthleteListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAthleteListStorage.class);

    private final Path filePath;

    /**
     * Constructs a {@code JsonAthleteListStorage} with the specified file path.
     *
     * @param filePath The path to the JSON file.
     */
    public JsonAthleteListStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the file path of the AthleteList JSON file.
     *
     * @return the file path.
     */
    @Override
    public Path getAthleteListFilePath() {
        return filePath;
    }

    /**
     * Reads the athlete list data from the default JSON file path.
     *
     * @return An {@code Optional} containing the athlete list if the file exists.
     * @throws DataLoadingException if there were errors loading the data
     *                              (e.g., illegal values or invalid JSON format).
     */
    @Override
    public Optional<ReadOnlyAthleteList> readAthleteList() throws DataLoadingException {
        return readAthleteList(filePath);
    }

    /**
     * Similar to {@link #readAthleteList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    @Override
    public Optional<ReadOnlyAthleteList> readAthleteList(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableAthleteList> json = JsonUtil.readJsonFile(
                filePath, JsonSerializableAthleteList.class);
        if (!json.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(json.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    /**
     * Saves the given athlete list data to the default JSON file path.
     *
     * @param athletes The athlete list data to save. Cannot be null.
     * @throws IOException if there was a problem writing to the file.
     */
    @Override
    public void saveAthleteList(ReadOnlyAthleteList athletes) throws IOException {
        saveAthleteList(athletes, filePath);
    }

    /**
     * Saves the given athlete list data to the specified JSON file path.
     * Creates the file if it does not exist.
     *
     * @param athletes The athlete list data to save. Cannot be null.
     * @param filePath The location of the data file. Cannot be null.
     * @throws IOException if there was a problem writing to the file.
     */
    @Override
    public void saveAthleteList(ReadOnlyAthleteList athletes, Path filePath) throws IOException {
        requireNonNull(athletes);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAthleteList(athletes), filePath);
    }
}
