package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.organization.ReadOnlyOrganizationList;

/**
 * Represents a storage for a list of {@link seedu.address.model.organization.Organization}.
 */
public interface OrganizationListStorage {

    /** Returns the file path of the organization list data file. */
    Path getOrganizationListFilePath();

    /**
     * Returns organization list data as a {@code ReadOnlyOrganizationList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyOrganizationList> readOrganizationList() throws DataLoadingException;

    /**
     * @see #getOrganizationListFilePath()
     */
    Optional<ReadOnlyOrganizationList> readOrganizationList(Path filePath) throws DataLoadingException;

    /**
     * Saves the given list of organizations to the storage.
     * @param organizations cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveOrganizationList(ReadOnlyOrganizationList organizations) throws IOException;

    /**
     * @see #saveOrganizationList(ReadOnlyOrganizationList)
     */
    void saveOrganizationList(ReadOnlyOrganizationList organizations, Path filePath) throws IOException;
}
