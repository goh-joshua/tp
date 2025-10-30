package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.contract.Contract;
import seedu.address.model.organization.Organization;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    /**
     * Executes the user command.
     * <p>
     * This method:
     * <ul>
     *   <li>Logs the command entered by the user</li>
     *   <li>Parses it into a {@link Command}</li>
     *   <li>Executes the command on the {@link Model}</li>
     *   <li>Attempts to save any modified data to storage</li>
     * </ul>
     *
     * @param commandText The raw command input by the user.
     * @return The {@link CommandResult} produced by executing the command.
     * @throws CommandException If an error occurs during command execution or data saving.
     * @throws ParseException If the command text cannot be parsed.
     */
    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
            storage.saveAthleteList(model.getAthleteList());
            storage.saveContractList(model.getContractList());
            storage.saveOrganizationList(model.getOrganizationList());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    /**
     * Returns the current read-only {@link ReadOnlyAddressBook} from the model.
     *
     * @return The address book data.
     */
    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    /**
     * Returns the file path where the address book data is stored.
     *
     * @return The {@link Path} to the address book file.
     */
    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    /**
     * Returns the current GUI settings of the application.
     *
     * @return The {@link GuiSettings} stored in the model.
     */
    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    /**
     * Updates the GUI settings in the model.
     *
     * @param guiSettings The new GUI settings to apply.
     */
    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    /**
     * Returns an unmodifiable view of the filtered list of contracts.
     *
     * @return The filtered {@link ObservableList} of {@link Contract}.
     */
    @Override
    public ObservableList<Contract> getFilteredContractList() {
        return model.getFilteredContractList();
    }

    /**
     * Returns an unmodifiable view of the filtered list of athletes.
     *
     * @return The filtered {@link ObservableList} of {@link Athlete}.
     */
    @Override
    public ObservableList<Athlete> getFilteredAthleteList() {
        return model.getFilteredAthleteList();
    }

    /**
     * Returns an unmodifiable view of the filtered list of organizations.
     *
     * @return The filtered {@link ObservableList} of {@link Organization}.
     */
    @Override
    public ObservableList<Organization> getFilteredOrganizationList() {
        return model.getFilteredOrganizationList();
    }

    /**
     * Returns an unmodifiable view of the list of athlete.
     *
     * @return The {@link ObservableList} of {@link Athlete}.
     */
    @Override
    public ObservableList<Athlete> getAllAthleteList() {
        return model.getAthleteList().getAthleteList();
    }

    /**
     * Returns an unmodifiable view of the list of organizations.
     *
     * @return The {@link ObservableList} of {@link Organization}.
     */
    @Override
    public ObservableList<Organization> getAllOrganizationList() {
        return model.getOrganizationList().getOrganizationList();
    }

    /**
     * Returns an unmodifiable view of the list of contracts.
     *
     * @return The {@link ObservableList} of {@link Contract}.
     */
    @Override
    public ObservableList<Contract> getAllContractList() {
        return model.getContractList().getContractList();
    }

}
