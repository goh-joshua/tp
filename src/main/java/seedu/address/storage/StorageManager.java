package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.athlete.ReadOnlyAthleteList;
import seedu.address.model.contract.ReadOnlyContractList;
import seedu.address.model.organization.ReadOnlyOrganizationList;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private AthleteListStorage athleteListStorage;
    private ContractListStorage contractListStorage;
    private OrganizationListStorage organizationListStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage},
     * {@code UserPrefStorage}, {@code AthleteListStorage}, {@code ContractListStorage}
     * and {@code OrganizationListStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage,
                          UserPrefsStorage userPrefsStorage,
                          AthleteListStorage athleteListStorage,
                          ContractListStorage contractListStorage,
                          OrganizationListStorage organizationListStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.athleteListStorage = athleteListStorage;
        this.contractListStorage = contractListStorage;
        this.organizationListStorage = organizationListStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ AthleteList methods ==============================

    @Override
    public Path getAthleteListFilePath() {
        return athleteListStorage.getAthleteListFilePath();
    }

    @Override
    public Optional<ReadOnlyAthleteList> readAthleteList() throws DataLoadingException {
        return readAthleteList(athleteListStorage.getAthleteListFilePath());
    }

    @Override
    public Optional<ReadOnlyAthleteList> readAthleteList(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return athleteListStorage.readAthleteList(filePath);
    }

    @Override
    public void saveAthleteList(ReadOnlyAthleteList athletes) throws IOException {
        saveAthleteList(athletes, athleteListStorage.getAthleteListFilePath());
    }

    @Override
    public void saveAthleteList(ReadOnlyAthleteList athletes, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        athleteListStorage.saveAthleteList(athletes, filePath);
    }

    // ================ ContractList methods ==============================

    @Override
    public Path getContractListFilePath() {
        return contractListStorage.getContractListFilePath();
    }

    @Override
    public Optional<ReadOnlyContractList> readContractList() throws DataLoadingException {
        return readContractList(contractListStorage.getContractListFilePath());
    }

    @Override
    public Optional<ReadOnlyContractList> readContractList(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return contractListStorage.readContractList(filePath);
    }

    @Override
    public void saveContractList(ReadOnlyContractList contracts) throws IOException {
        saveContractList(contracts, contractListStorage.getContractListFilePath());
    }

    @Override
    public void saveContractList(ReadOnlyContractList contracts, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        contractListStorage.saveContractList(contracts, filePath);
    }

    // ================ OrganizationList methods ==============================

    @Override
    public Path getOrganizationListFilePath() {
        return organizationListStorage.getOrganizationListFilePath();
    }

    @Override
    public Optional<ReadOnlyOrganizationList> readOrganizationList() throws DataLoadingException {
        return readOrganizationList(organizationListStorage.getOrganizationListFilePath());
    }

    @Override
    public Optional<ReadOnlyOrganizationList> readOrganizationList(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return organizationListStorage.readOrganizationList(filePath);
    }

    @Override
    public void saveOrganizationList(ReadOnlyOrganizationList organizations) throws IOException {
        saveOrganizationList(organizations, organizationListStorage.getOrganizationListFilePath());
    }

    @Override
    public void saveOrganizationList(ReadOnlyOrganizationList organizations, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        organizationListStorage.saveOrganizationList(organizations, filePath);
    }

}
