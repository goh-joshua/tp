package seedu.address.commons.core;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Config values used by the app
 */
public class Config {

    public static final Path DEFAULT_CONFIG_FILE = Paths.get("config.json");

    // Config values customizable through config file
    private Level logLevel = Level.INFO;
    private Path userPrefsFilePath = Paths.get("preferences.json");

    /**
     * Returns the current log level.
     *
     * @return The log level for the application.
     */
    public Level getLogLevel() {
        return logLevel;
    }

    /**
     * Sets the log level for the application.
     *
     * @param logLevel The log level to set.
     */
    public void setLogLevel(Level logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * Returns the file path for user preferences.
     *
     * @return The path to the user preferences file.
     */
    public Path getUserPrefsFilePath() {
        return userPrefsFilePath;
    }

    /**
     * Sets the file path for user preferences.
     *
     * @param userPrefsFilePath The path to set for the user preferences file.
     */
    public void setUserPrefsFilePath(Path userPrefsFilePath) {
        this.userPrefsFilePath = userPrefsFilePath;
    }

    /**
     * Returns true if both Config objects have the same configuration values.
     *
     * @param other The other object to compare with.
     * @return True if both objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Config)) {
            return false;
        }

        Config otherConfig = (Config) other;
        return Objects.equals(logLevel, otherConfig.logLevel)
                && Objects.equals(userPrefsFilePath, otherConfig.userPrefsFilePath);
    }

    /**
     * Returns the hash code for this Config object.
     *
     * @return The hash code based on logLevel and userPrefsFilePath.
     */
    @Override
    public int hashCode() {
        return Objects.hash(logLevel, userPrefsFilePath);
    }

    /**
     * Returns a string representation of this Config object.
     *
     * @return A formatted string containing the configuration values.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("logLevel", logLevel)
                .add("userPrefsFilePath", userPrefsFilePath)
                .toString();
    }

}
