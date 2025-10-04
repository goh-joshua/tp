package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class AthleteUserPrefs implements ReadOnlyAthleteUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path filePath = Paths.get("data" , "playbook.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public AthleteUserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code athleteUserPrefs}.
     */
    public AthleteUserPrefs(ReadOnlyAthleteUserPrefs athleteUserPrefs) {
        this();
        resetData(athleteUserPrefs);
    }

    /**
     * Resets the existing data of this {@code AthleteUserPrefs} with {@code newAthleteUserPrefs}.
     */
    public void resetData(ReadOnlyAthleteUserPrefs newAthleteUserPrefs) {
        requireNonNull(newAthleteUserPrefs);
        setGuiSettings(newAthleteUserPrefs.getGuiSettings());
        setFilePath(newAthleteUserPrefs.getFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        requireNonNull(filePath);
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AthleteUserPrefs)) {
            return false;
        }

        AthleteUserPrefs otherAthleteUserPrefs = (AthleteUserPrefs) other;
        return guiSettings.equals(otherAthleteUserPrefs.guiSettings)
                && filePath.equals(otherAthleteUserPrefs.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, filePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + filePath);
        return sb.toString();
    }

}
