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

    public JsonAthleteListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getAthleteListFilePath() {
        return filePath;
    }

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

    @Override
    public void saveAthleteList(ReadOnlyAthleteList athletes) throws IOException {
        saveAthleteList(athletes, filePath);
    }

    @Override
    public void saveAthleteList(ReadOnlyAthleteList athletes, Path filePath) throws IOException {
        requireNonNull(athletes);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAthleteList(athletes), filePath);
    }
}
