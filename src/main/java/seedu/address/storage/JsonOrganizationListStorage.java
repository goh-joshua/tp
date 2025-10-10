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

    public JsonOrganizationListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getOrganizationListFilePath() {
        return filePath;
    }

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

    @Override
    public void saveOrganizationList(ReadOnlyOrganizationList organizations) throws IOException {
        saveOrganizationList(organizations, filePath);
    }

    @Override
    public void saveOrganizationList(ReadOnlyOrganizationList organizations, Path filePath) throws IOException {
        requireNonNull(organizations);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableOrganizationList(organizations), filePath);
    }
}
