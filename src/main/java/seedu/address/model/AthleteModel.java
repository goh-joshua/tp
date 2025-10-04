package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.athlete.Athlete;

/**
 * The API of the Model component.
 */
public interface AthleteModel {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Athlete> PREDICATE_SHOW_ALL_ATHLETES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setAthleteUserPrefs(ReadOnlyAthleteUserPrefs athleteUserPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyAthleteUserPrefs getAthleteUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' file path.
     */
    Path getFilePath();

    /**
     * Sets the user prefs' file path.
     */
    void setFilePath(Path filePath);

    /**
     * Replaces data with the data in {@code athleteBook}.
     */
    void setAthleteBook(ReadOnlyAthleteBook athleteBook);

    /** Returns the AthleteBook */
    ReadOnlyAthleteBook getAthleteBook();

    /**
     * Returns true if an athlete with the same identity as {@code athlete} exists in playbook.io.
     */
    boolean hasAthlete(Athlete athlete);

    /**
     * Deletes the given athlete.
     * The athlete must exist in playbook.io.
     */
    void deleteAthlete(Athlete target);

    /**
     * Adds the given athlete.
     * {@code athlete} must not already exist in playbook.io.
     */
    void addAthlete(Athlete athlete);

    /** Returns an unmodifiable view of the filtered athlete list */
    ObservableList<Athlete> getFilteredAthleteList();

    /**
     * Updates the filter of the filtered athlete list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAthleteList(Predicate<Athlete> predicate);
}
