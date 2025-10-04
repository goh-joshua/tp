package seedu.address.logic;

import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AthleteCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AthleteBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AthleteModel;
import seedu.address.model.ReadOnlyAthleteBook;
import seedu.address.model.athlete.Athlete;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class AthleteLogicManager implements AthleteLogic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final AthleteModel model;
    private final Storage storage;
    private final AthleteBookParser athleteParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public AthleteLogicManager(AthleteModel model, Storage storage) {
        this.model = model;
        this.storage = storage;
        athleteParser = new AthleteBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        AthleteCommand command = athleteParser.parseCommand(commandText);
        commandResult = command.execute(model);

        /*  try {
           storage.saveAthleteBook(model.getAthleteBook());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }
        */

        return commandResult;
    }

    @Override
    public ReadOnlyAthleteBook getAthleteBook() {
        return model.getAthleteBook();
    }

    @Override
    public ObservableList<Athlete> getFilteredAthleteList() {
        return model.getFilteredAthleteList();
    }

    @Override
    public Path getFilePath() {
        return model.getFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
