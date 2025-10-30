package seedu.address.commons.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.core.Config;
import seedu.address.commons.exceptions.DataLoadingException;

/**
 * A class for accessing the Config File.
 */
public class ConfigUtil {

    /**
     * Reads the configuration from the specified file path.
     *
     * @param configFilePath The path to the config file.
     * @return An Optional containing the Config if the file exists and is valid, or an empty Optional otherwise.
     * @throws DataLoadingException If there is an error reading or parsing the config file.
     */
    public static Optional<Config> readConfig(Path configFilePath) throws DataLoadingException {
        return JsonUtil.readJsonFile(configFilePath, Config.class);
    }

    /**
     * Saves the configuration to the specified file path.
     *
     * @param config The Config object to save.
     * @param configFilePath The path where the config file should be saved.
     * @throws IOException If there is an error writing to the file.
     */
    public static void saveConfig(Config config, Path configFilePath) throws IOException {
        JsonUtil.saveJsonFile(config, configFilePath);
    }

}
