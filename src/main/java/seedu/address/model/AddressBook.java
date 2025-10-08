package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.UniqueAthleteList;
import seedu.address.model.contract.Contract;
import seedu.address.model.contract.UniqueContractList;
import seedu.address.model.organization.Organization;
import seedu.address.model.organization.UniqueOrganizationList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Wraps all data at the address-book level.
 * Duplicates are not allowed for any entity type (based on .isSameX comparisons).
 */
public class AddressBook implements ReadOnlyAddressBook {

    // ============================================================
    // Internal Data Structures
    // ============================================================

    private final UniquePersonList persons;
    private final UniqueOrganizationList organizations;
    private final UniqueAthleteList athletes;
    private final UniqueContractList contracts;

    /*
     * Non-static initialization block to ensure all collections
     * are initialized for all constructors.
     */
    {
        persons = new UniquePersonList();
        organizations = new UniqueOrganizationList();
        athletes = new UniqueAthleteList();
        contracts = new UniqueContractList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the data in {@code toBeCopied}.
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    // ============================================================
    // Overwrite Operations
    // ============================================================

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the organization list with {@code organizations}.
     * {@code organizations} must not contain duplicate organizations.
     */
    public void setOrganizations(List<Organization> organizations) {
        this.organizations.setOrganizations(organizations);
    }

    /**
     * Replaces the contents of the athlete list with {@code athletes}.
     * {@code athletes} must not contain duplicate athletes.
     */
    public void setAthletes(List<Athlete> athletes) {
        this.athletes.setAthletes(athletes);
    }

    /**
     * Replaces the contents of the contract list with {@code contracts}.
     * {@code contracts} must not contain duplicate contracts.
     */
    public void setContracts(List<Contract> contracts) {
        this.contracts.setContracts(contracts);
    }

    /**
     * Resets this AddressBook’s data with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setOrganizations(newData.getOrganizationList());
        setAthletes(newData.getAthleteList());
        setContracts(newData.getContractList());
    }

    // ============================================================
    // Person-Level Operations
    // ============================================================

    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    public void addPerson(Person p) {
        persons.add(p);
    }

    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    public void removePerson(Person key) {
        persons.remove(key);
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    // ============================================================
    // Organization-Level Operations
    // ============================================================

    public boolean hasOrganization(Organization organization) {
        requireNonNull(organization);
        return organizations.contains(organization);
    }

    public void addOrganization(Organization organization) {
        organizations.add(organization);
    }

    public void setOrganization(Organization target, Organization editedOrganization) {
        requireNonNull(editedOrganization);
        organizations.setOrganization(target, editedOrganization);
    }

    public void removeOrganization(Organization target) {
        organizations.remove(target);
    }

    public ObservableList<Organization> getOrganizationList() {
        return organizations.asUnmodifiableObservableList();
    }

    // ============================================================
    // Athlete-Level Operations
    // ============================================================

    public boolean hasAthlete(Athlete athlete) {
        requireNonNull(athlete);
        return athletes.contains(athlete);
    }

    public void addAthlete(Athlete athlete) {
        athletes.add(athlete);
    }

    public void removeAthlete(Athlete athlete) {
        athletes.remove(athlete);
    }

    @Override
    public ObservableList<Athlete> getAthleteList() {
        return athletes.asUnmodifiableObservableList();
    }

    // ============================================================
    // Contract-Level Operations
    // ============================================================

    public boolean hasContract(Contract contract) {
        requireNonNull(contract);
        return contracts.contains(contract);
    }

    public void addContract(Contract contract) {
        contracts.add(contract);
    }

    public void removeContract(Contract contract) {
        contracts.remove(contract);
    }

    @Override
    public ObservableList<Contract> getContractList() {
        return contracts.asUnmodifiableObservableList();
    }

    // ============================================================
    // Utility Methods
    // ============================================================

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .add("organizations", organizations)
                .add("athletes", athletes)
                .add("contracts", contracts)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherBook = (AddressBook) other;
        return persons.equals(otherBook.persons)
                && organizations.equals(otherBook.organizations)
                && athletes.equals(otherBook.athletes)
                && contracts.equals(otherBook.contracts);
    }

    @Override
    public int hashCode() {
        int result = persons.hashCode();
        result = 31 * result + organizations.hashCode();
        result = 31 * result + athletes.hashCode();
        result = 31 * result + contracts.hashCode();
        return result;
    }
}
