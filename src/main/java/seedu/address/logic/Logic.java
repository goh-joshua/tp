package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.contract.Contract;
import seedu.address.model.organization.Organization;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns an unmodifiable view of the filtered list of contracts.
     *
     * @see seedu.address.model.Model#getFilteredContractList()
     */
    ObservableList<Contract> getFilteredContractList();

    /** Returns an unmodifiable view of the filtered list of athletes */
    ObservableList<Athlete> getFilteredAthleteList();

    /** Returns an unmodifiable view of the filtered list of organizations */
    ObservableList<Organization> getFilteredOrganizationList();

    /** Returns an unmodifiable view of the filtered list of contracts */
    ObservableList<Contract> getAllContractList();

    /** Returns an unmodifiable view of the filtered list of Athletes */
    ObservableList<Athlete> getAllAthleteList();

    /** Returns an unmodifiable view of the filtered list of Organizations */
    ObservableList<Organization> getAllOrganizationList();
}
