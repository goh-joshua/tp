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

/**
 * Represents the in-memory model of the playbook.io data.
 */
public class AthleteModelManager implements AthleteModel {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AthleteBook athleteBook;
    private final AthleteUserPrefs athleteUserPrefs;
    private final FilteredList<Athlete> filteredAthletes;

    /**
     * Initializes a AthleteModelManager with the given athleteBook and athleteUserPrefs.
     */
    public AthleteModelManager(ReadOnlyAthleteBook athleteBook, ReadOnlyAthleteUserPrefs athleteUserPrefs) {
        requireAllNonNull(athleteBook, athleteUserPrefs);

        logger.fine("Initializing with address book: " + athleteBook + " and user prefs " + athleteUserPrefs);

        this.athleteBook = new AthleteBook(athleteBook);
        this.athleteUserPrefs = new AthleteUserPrefs(athleteUserPrefs);
        filteredAthletes = new FilteredList<>(this.athleteBook.getAthleteList());
    }

    public AthleteModelManager() {
        this(new AthleteBook(), new AthleteUserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setAthleteUserPrefs(ReadOnlyAthleteUserPrefs athleteUserPrefs) {
        requireNonNull(athleteUserPrefs);
        this.athleteUserPrefs.resetData(athleteUserPrefs);
    }

    @Override
    public ReadOnlyAthleteUserPrefs getAthleteUserPrefs() {
        return athleteUserPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return athleteUserPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        athleteUserPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getFilePath() {
        return athleteUserPrefs.getFilePath();
    }

    @Override
    public void setFilePath(Path filePath) {
        requireNonNull(filePath);
        athleteUserPrefs.setFilePath(filePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAthleteBook(ReadOnlyAthleteBook athleteBook) {
        this.athleteBook.resetData(athleteBook);
    }

    @Override
    public ReadOnlyAthleteBook getAthleteBook() {
        return athleteBook;
    }

    @Override
    public boolean hasAthlete(Athlete athlete) {
        requireNonNull(athlete);
        return athleteBook.hasAthlete(athlete);
    }

    @Override
    public void deleteAthlete(Athlete target) {
        athleteBook.removeAthlete(target);
    }

    @Override
    public void addAthlete(Athlete athlete) {
        athleteBook.addAthlete(athlete);
        updateFilteredAthleteList(PREDICATE_SHOW_ALL_ATHLETES);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Athlete} backed by the internal list of
     * {@code versionedAthleteBook}
     */
    @Override
    public ObservableList<Athlete> getFilteredAthleteList() {
        return filteredAthletes;
    }

    @Override
    public void updateFilteredAthleteList(Predicate<Athlete> predicate) {
        requireNonNull(predicate);
        filteredAthletes.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AthleteModelManager)) {
            return false;
        }

        AthleteModelManager otherAthleteModelManager = (AthleteModelManager) other;
        return athleteBook.equals(otherAthleteModelManager.athleteBook)
                && athleteUserPrefs.equals(otherAthleteModelManager.athleteUserPrefs)
                && filteredAthletes.equals(otherAthleteModelManager.filteredAthletes);
    }

}
