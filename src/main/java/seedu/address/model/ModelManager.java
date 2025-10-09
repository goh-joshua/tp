package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.AthleteList;
import seedu.address.model.athlete.ReadOnlyAthleteList;
import seedu.address.model.contract.Contract;
import seedu.address.model.organization.Organization;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of all app data using a single AddressBook:
 * - Persons, Contracts, Organizations (placeholder)
 * - Athletes (now stored in AddressBook as well)
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    // ---- Single AddressBook domain ----
    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final AthleteList athleteList;

    // filtered views
    private final FilteredList<Person> filteredPersons;
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
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyAthleteList athleteList) {
        requireAllNonNull(addressBook, userPrefs, athleteList);

        logger.fine("Initializing with address book: " + addressBook
            + " | user prefs: " + userPrefs);
        logger.fine("Initializing athlete list: " + athleteList);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.athleteList = new AthleteList(athleteList);

        this.filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.filteredContracts = new FilteredList<>(this.addressBook.getContractList());
        this.filteredOrganizations = new FilteredList<>(FXCollections.observableArrayList()); // placeholder
        this.filteredAthletes = new FilteredList<>(this.addressBook.getAthleteList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new AthleteList());
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
    // AddressBook (Persons / Contracts / Orgs / Athletes)
    // =====================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    // ---- Persons ----
    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    // ---- Contracts ----
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

    // ---- Organizations (placeholders) ----
    @Override
    public boolean hasOrganization(Organization organization) {
        requireNonNull(organization);
        throw new UnsupportedOperationException("Organization support not yet implemented");
    }

    @Override
    public void deleteOrganization(Organization target) {
        requireNonNull(target);
        throw new UnsupportedOperationException("Organization support not yet implemented");
    }

    @Override
    public void addOrganization(Organization organization) {
        requireNonNull(organization);
        throw new UnsupportedOperationException("Organization support not yet implemented");
    }

    @Override
    public void setOrganization(Organization target, Organization editedOrganization) {
        requireAllNonNull(target, editedOrganization);
        throw new UnsupportedOperationException("Organization support not yet implemented");
    }

    @Override
    public ObservableList<Organization> getFilteredOrganizationList() {
        return filteredOrganizations;
    }

    @Override
    public void updateFilteredOrganizationList(Predicate<Organization> predicate) {
        requireNonNull(predicate);
        throw new UnsupportedOperationException("Organization filtering not yet implemented");
    }

    // ---- Athletes (now from addressBook) ----

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
                && filteredPersons.equals(o.filteredPersons)
                && filteredContracts.equals(o.filteredContracts)
                && filteredAthletes.equals(o.filteredAthletes);
    }
}
