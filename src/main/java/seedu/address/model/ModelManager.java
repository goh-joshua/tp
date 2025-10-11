package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.AthleteList;
import seedu.address.model.athlete.ReadOnlyAthleteList;
import seedu.address.model.contract.Contract;
import seedu.address.model.contract.ContractList;
import seedu.address.model.contract.ReadOnlyContractList;
import seedu.address.model.organization.Organization;
import seedu.address.model.organization.OrganizationList;
import seedu.address.model.organization.ReadOnlyOrganizationList;

/**
 * Represents the in-memory model of all app data using a single AddressBook:
 * - Athletes, Contracts, Organizations
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    // ---- Single AddressBook domain ----
    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final AthleteList athleteList;
    private final ContractList contractList;
    private final OrganizationList organizationList;

    // filtered views
    private final FilteredList<Organization> filteredOrganizations; // placeholder until wired
    private final FilteredList<Contract> filteredContracts;
    private final FilteredList<Athlete> filteredAthletes;

    // =====================================================================================
    // Constructors
    // =====================================================================================

    /**
     * Constructs a {@code ModelManager} with the given address book and user preferences.
     *
     * @param addressBook the address book data to initialize from
     * @param userPrefs   the user preferences to initialize from
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyAthleteList athleteList, ReadOnlyContractList contractList,
                        ReadOnlyOrganizationList organizationList) {
        requireAllNonNull(addressBook, userPrefs, athleteList, contractList, organizationList);

        logger.fine("Initializing with address book: " + addressBook
            + " | user prefs: " + userPrefs);
        logger.fine("Initializing athlete list: " + athleteList);
        logger.fine("Initializing contract list: " + contractList);
        logger.fine("Initializing organization list: " + organizationList);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.athleteList = new AthleteList(athleteList);
        this.contractList = new ContractList(contractList);
        this.organizationList = new OrganizationList(organizationList);

        // Populate addressBook with the loaded athletes
        for (Athlete athlete : athleteList.getAthleteList()) {
            this.addressBook.addAthlete(athlete);
        }

        // Populate addressBook with the loaded contracts
        for (Contract contract : contractList.getContractList()) {
            this.addressBook.addContract(contract);
        }

        // Populate addressBook with the loaded organizations
        for (Organization organization : organizationList.getOrganizationList()) {
            this.addressBook.addOrganization(organization);
        }

        this.filteredContracts = new FilteredList<>(this.addressBook.getContractList());
        this.filteredOrganizations = new FilteredList<>(this.addressBook.getOrganizationList());
        this.filteredAthletes = new FilteredList<>(this.addressBook.getAthleteList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new AthleteList(), new ContractList(), new OrganizationList());
    }

    // =====================================================================================
    // UserPrefs (AddressBook)
    // =====================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    // =====================================================================================
    // AddressBook (Contracts / Orgs / Athletes)
    // =====================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    // ---- Contracts ----

    @Override
    public ReadOnlyContractList getContractList() {
        // Return a read-only view of the addressBook's contract list (where changes actually happen)
        return new ReadOnlyContractList() {
            @Override
            public ObservableList<Contract> getContractList() {
                return addressBook.getContractList();
            }
        };
    }

    @Override
    public boolean hasContract(Contract contract) {
        requireNonNull(contract);
        return addressBook.hasContract(contract);
    }

    @Override
    public void addContract(Contract contract) {
        requireNonNull(contract);
        addressBook.addContract(contract);
        updateFilteredContractList(PREDICATE_SHOW_ALL_CONTRACTS);
    }

    @Override
    public void deleteContract(Contract target) {
        requireNonNull(target);
        addressBook.removeContract(target);
    }

    @Override
    public ObservableList<Contract> getFilteredContractList() {
        return filteredContracts;
    }

    @Override
    public void updateFilteredContractList(Predicate<Contract> predicate) {
        requireNonNull(predicate);
        filteredContracts.setPredicate(predicate);
    }

    // ---- Organizations ----

    @Override
    public ReadOnlyOrganizationList getOrganizationList() {
        // Return a read-only view of the live organization list from addressBook
        return new ReadOnlyOrganizationList() {
            @Override
            public ObservableList<Organization> getOrganizationList() {
                return addressBook.getOrganizationList();
            }
        };
    }

    @Override
    public boolean hasOrganization(Organization organization) {
        requireNonNull(organization);
        return addressBook.hasOrganization(organization);
    }

    @Override
    public void deleteOrganization(Organization target) {
        requireNonNull(target);
        addressBook.removeOrganization(target);
    }

    @Override
    public void addOrganization(Organization organization) {
        requireNonNull(organization);
        addressBook.addOrganization(organization);
        updateFilteredOrganizationList(PREDICATE_SHOW_ALL_ORGANIZATIONS);
    }

    @Override
    public void setOrganization(Organization target, Organization editedOrganization) {
        requireAllNonNull(target, editedOrganization);
        addressBook.setOrganization(target, editedOrganization);
    }

    @Override
    public ObservableList<Organization> getFilteredOrganizationList() {
        return filteredOrganizations;
    }

    @Override
    public void updateFilteredOrganizationList(Predicate<Organization> predicate) {
        requireNonNull(predicate);
        filteredOrganizations.setPredicate(predicate);
    }

    // ---- Athletes ----

    @Override
    public ReadOnlyAthleteList getAthleteList() {
        // Return a read-only view of the addressBook's athlete list (where changes actually happen)
        return new ReadOnlyAthleteList() {
            @Override
            public ObservableList<Athlete> getAthleteList() {
                return addressBook.getAthleteList();
            }
        };
    }

    @Override
    public boolean hasAthlete(Athlete athlete) {
        requireNonNull(athlete);
        return addressBook.hasAthlete(athlete);
    }

    @Override
    public void deleteAthlete(Athlete target) {
        addressBook.removeAthlete(target);
    }

    @Override
    public void addAthlete(Athlete athlete) {
        addressBook.addAthlete(athlete);
        updateFilteredAthleteList(PREDICATE_SHOW_ALL_ATHLETES);
    }

    @Override
    public ObservableList<Athlete> getFilteredAthleteList() {
        return filteredAthletes;
    }

    @Override
    public void updateFilteredAthleteList(Predicate<Athlete> predicate) {
        requireNonNull(predicate);
        filteredAthletes.setPredicate(predicate);
    }

    // =====================================================================================
    // Equality
    // =====================================================================================

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager o = (ModelManager) other;
        return addressBook.equals(o.addressBook)
                && userPrefs.equals(o.userPrefs)
                && filteredContracts.equals(o.filteredContracts)
                && filteredAthletes.equals(o.filteredAthletes);
    }
}
