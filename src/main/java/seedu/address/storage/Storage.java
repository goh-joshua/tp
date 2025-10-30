package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage, AthleteListStorage,
        ContractListStorage, OrganizationListStorage {

    /**
     * Reads the user preferences from storage.
     *
     * @return An {@code Optional} containing the UserPrefs if available.
     * @throws DataLoadingException if there were errors reading from storage.
     */
    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    /**
     * Saves the user preferences to storage.
     *
     * @param userPrefs The user preferences to save. Cannot be null.
     * @throws IOException if there was an error writing to the file.
     */
    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    /**
     * Returns the file path of the AddressBook JSON data file.
     *
     * @return the AddressBook file path.
     */
    @Override
    Path getAddressBookFilePath();

    /**
     * Reads the AddressBook data from storage.
     *
     * @return An {@code Optional} containing the AddressBook if available.
     * @throws DataLoadingException if there were errors reading from storage.
     */
    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException;

    /**
     * Saves the AddressBook data to storage.
     *
     * @param addressBook The AddressBook data to save. Cannot be null.
     * @throws IOException if there was an error writing to the file.
     */
    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

}
