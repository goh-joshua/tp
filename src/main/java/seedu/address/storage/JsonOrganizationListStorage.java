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
import seedu.address.model.organization.ReadOnlyOrganizationList;

/**
 * A class to access organization list data stored as a json file on the hard disk.
 */
public class JsonOrganizationListStorage implements OrganizationListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonOrganizationListStorage.class);

    private final Path filePath;

    /**
     * Constructs a {@code JsonOrganizationListStorage} with the specified file path.
     *
     * @param filePath The path to the JSON file.
     */
    public JsonOrganizationListStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the file path of the OrganizationList JSON file.
     *
     * @return the file path.
     */
    @Override
    public Path getOrganizationListFilePath() {
        return filePath;
    }

    /**
     * Reads the organization list data from the default JSON file path.
     *
     * @return An {@code Optional} containing the organization list if the file exists.
     * @throws DataLoadingException if there were errors loading the data
     *                              (e.g., illegal values or invalid JSON format).
     */
    @Override
    public Optional<ReadOnlyOrganizationList> readOrganizationList() throws DataLoadingException {
        return readOrganizationList(filePath);
    }

    /**
     * Similar to {@link #readOrganizationList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    @Override
    public Optional<ReadOnlyOrganizationList> readOrganizationList(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableOrganizationList> json = JsonUtil.readJsonFile(
                filePath, JsonSerializableOrganizationList.class);
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
     * Saves the given organization list data to the default JSON file path.
     *
     * @param organizations The organization list data to save. Cannot be null.
     * @throws IOException if there was a problem writing to the file.
     */
    @Override
    public void saveOrganizationList(ReadOnlyOrganizationList organizations) throws IOException {
        saveOrganizationList(organizations, filePath);
    }

    /**
     * Saves the given organization list data to the specified JSON file path.
     * Creates the file if it does not exist.
     *
     * @param organizations The organization list data to save. Cannot be null.
     * @param filePath      The location of the data file. Cannot be null.
     * @throws IOException if there was a problem writing to the file.
     */
    @Override
    public void saveOrganizationList(ReadOnlyOrganizationList organizations, Path filePath) throws IOException {
        requireNonNull(organizations);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableOrganizationList(organizations), filePath);
    }
}
