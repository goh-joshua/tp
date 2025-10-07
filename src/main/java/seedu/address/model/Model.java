package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.organization.Organization;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * {@code Predicate} that always evaluates to true
     */
    Predicate<Organization> PREDICATE_SHOW_ALL_ORGANIZATIONS = unused -> true;

    // ------------------------------------------------------------------------
    // User preferences
    // ------------------------------------------------------------------------

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    // ------------------------------------------------------------------------
    // AddressBook data
    // ------------------------------------------------------------------------

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    // ------------------------------------------------------------------------
    // Person operations
    // ------------------------------------------------------------------------

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns an unmodifiable view of the filtered person list.
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    // ------------------------------------------------------------------------
    // Organization operations
    // ------------------------------------------------------------------------

    /**
     * Returns true if an organization with the same identity as {@code organization}
     * exists in the address book.
     */
    boolean hasOrganization(Organization organization);

    /**
     * Deletes the given organization.
     * The organization must exist in the address book.
     */
    void deleteOrganization(Organization target);

    /**
     * Adds the given organization.
     * {@code organization} must not already exist in the address book.
     */
    void addOrganization(Organization organization);

    /**
     * Replaces the given organization {@code target} with {@code editedOrganization}.
     * {@code target} must exist in the address book.
     * The organization identity of {@code editedOrganization} must not be the same as another
     * existing organization in the address book.
     */
    void setOrganization(Organization target, Organization editedOrganization);

    /**
     * Returns an unmodifiable view of the filtered organization list.
     */
    ObservableList<Organization> getFilteredOrganizationList();

    /**
     * Updates the filter of the filtered organization list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOrganizationList(Predicate<Organization> predicate);
}
