package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.contract.ReadOnlyContractList;

/**
 * Represents a storage for a list of {@link seedu.address.model.contract.Contract}.
 */
public interface ContractListStorage {

    /** Returns the file path of the contract list data file. */
    Path getContractListFilePath();

    /**
     * Returns contract list data as a {@code ReadOnlyContractList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyContractList> readContractList() throws DataLoadingException;

    /**
     * @see #getContractListFilePath()
     */
    Optional<ReadOnlyContractList> readContractList(Path filePath) throws DataLoadingException;

    /**
     * Saves the given list of contracts to the storage.
     * @param contracts cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveContractList(ReadOnlyContractList contracts) throws IOException;

    /**
     * @see #saveContractList(ReadOnlyContractList)
     */
    void saveContractList(ReadOnlyContractList contracts, Path filePath) throws IOException;
}
