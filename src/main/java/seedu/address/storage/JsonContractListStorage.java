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
import seedu.address.model.contract.ReadOnlyContractList;

/**
 * A class to access contract list data stored as a json file on the hard disk.
 */
public class JsonContractListStorage implements ContractListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonContractListStorage.class);

    private final Path filePath;

    public JsonContractListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getContractListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyContractList> readContractList() throws DataLoadingException {
        return readContractList(filePath);
    }

    /**
     * Similar to {@link #readContractList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    @Override
    public Optional<ReadOnlyContractList> readContractList(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableContractList> json = JsonUtil.readJsonFile(
                filePath, JsonSerializableContractList.class);
        if (!json.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(json.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info(String.format("Illegal values found in %s: %s", filePath, ive.getMessage()));
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveContractList(ReadOnlyContractList contracts) throws IOException {
        saveContractList(contracts, filePath);
    }

    @Override
    public void saveContractList(ReadOnlyContractList contracts, Path filePath) throws IOException {
        requireNonNull(contracts);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableContractList(contracts), filePath);
    }
}
