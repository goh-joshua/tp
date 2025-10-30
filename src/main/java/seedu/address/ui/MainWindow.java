package seedu.address.ui;

import java.awt.Point;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private AthleteListPanel athleteListPanel;
    private OrganizationListPanel organizationListPanel;
    private ContractListPanel contractListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private TabPane tabPane;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane athleteListPanelPlaceholder;

    @FXML
    private StackPane organizationListPanelPlaceholder;

    @FXML
    private StackPane contractListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
        setTabAccelerators();
        setRefreshAccelerator();
    }

    /**
     * Sets up keyboard shortcuts for tab switching.
     */
    private void setTabAccelerators() {
        setTabAccelerator(KeyCombination.valueOf("Shortcut+1"), 0); // Athletes tab
        setTabAccelerator(KeyCombination.valueOf("Shortcut+2"), 1); // Organizations tab
        setTabAccelerator(KeyCombination.valueOf("Shortcut+3"), 2); // Contracts tab
    }

    /**
     * Sets up a keyboard shortcut to switch to a specific tab.
     * @param keyCombination the key combination to trigger the tab switch
     * @param tabIndex the index of the tab to switch to
     */
    private void setTabAccelerator(KeyCombination keyCombination, int tabIndex) {
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (keyCombination.match(event)) {
                if (tabPane != null && tabIndex < tabPane.getTabs().size()) {
                    tabPane.getSelectionModel().select(tabIndex);
                }
                event.consume();
            }
        });
    }

    /**
     * Sets up keyboard shortcut for refresh command (Cmd+R or Ctrl+R).
     */
    private void setRefreshAccelerator() {
        KeyCombination refreshKeyCombination = KeyCombination.valueOf("Shortcut+R");
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (refreshKeyCombination.match(event)) {
                try {
                    executeCommand("refresh");
                } catch (CommandException | ParseException e) {
                    logger.warning("Failed to execute refresh command via keyboard shortcut: " + e.getMessage());
                }
                event.consume();
            }
        });
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        athleteListPanel = new AthleteListPanel(logic.getFilteredAthleteList(),
        logic.getFilteredContractList(), logic.getAllContractList());
        athleteListPanelPlaceholder.getChildren().add(athleteListPanel.getRoot());

        organizationListPanel = new OrganizationListPanel(logic.getFilteredOrganizationList(),
                logic.getFilteredContractList(), logic.getAllContractList());
        organizationListPanelPlaceholder.getChildren().add(organizationListPanel.getRoot());

        contractListPanel = new ContractListPanel(logic.getFilteredContractList());
        contractListPanelPlaceholder.getChildren().add(contractListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        double width = guiSettings.getWindowWidth();
        double height = guiSettings.getWindowHeight();

        Point savedCoordinates = guiSettings.getWindowCoordinates();
        if (savedCoordinates != null) {
            Rectangle2D savedBounds = findBoundsFor(savedCoordinates.getX(), savedCoordinates.getY(),
                    width, height);
            if (savedBounds != null) {
                width = Math.min(width, savedBounds.getWidth());
                height = Math.min(height, savedBounds.getHeight());
                double targetX = clamp(savedCoordinates.getX(),
                        savedBounds.getMinX(),
                        savedBounds.getMaxX() - width);
                double targetY = clamp(savedCoordinates.getY(),
                        savedBounds.getMinY(),
                        savedBounds.getMaxY() - height);
                applyStageBounds(width, height, targetX, targetY);
                return;
            }
        }

        Rectangle2D primaryBounds = Screen.getPrimary().getVisualBounds();
        width = Math.min(width, primaryBounds.getWidth());
        height = Math.min(height, primaryBounds.getHeight());
        double centerX = primaryBounds.getMinX() + (primaryBounds.getWidth() - width) / 2;
        double centerY = primaryBounds.getMinY() + (primaryBounds.getHeight() - height) / 2;
        applyStageBounds(width, height, centerX, centerY);
    }

    private Rectangle2D findBoundsFor(double x, double y, double width, double height) {
        return Screen.getScreens().stream()
                .map(Screen::getVisualBounds)
                .filter(bounds -> bounds.contains(x, y) || bounds.intersects(x, y, width, height))
                .findFirst()
                .orElse(null);
    }

    private void applyStageBounds(double width, double height, double x, double y) {
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setX(x);
        primaryStage.setY(y);
    }

    private double clamp(double value, double min, double max) {
        if (min > max) {
            return min;
        }
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     * This method properly handles minimized windows by restoring them.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            // If window is minimized, restore it
            if (helpWindow.isIconified()) {
                helpWindow.setIconified(false);
            }
            helpWindow.toFront();
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            commandResult.getTabToShow().ifPresent(this::switchToTab);

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    private void switchToTab(CommandResult.UiTab tab) {
        if (tabPane != null) {
            tabPane.getSelectionModel().select(tab.getTabIndex());
        }
    }
}
