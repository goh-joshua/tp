package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.athlete.ReadOnlyAthleteList;

/**
 * Represents a storage for a list of {@link seedu.address.model.athlete.Athlete}.
 */
public interface AthleteListStorage {

    /** Returns the file path of the athlete list data file. */
    Path getAthleteListFilePath();

    /**
     * Returns athlete list data as a {@code ReadOnlyAthleteList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyAthleteList> readAthleteList() throws DataLoadingException;

    /**
     * @see #getAthleteListFilePath()
     */
    Optional<ReadOnlyAthleteList> readAthleteList(Path filePath) throws DataLoadingException;

    /**
     * Saves the given list of athletes to the storage.
     * @param athletes cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAthleteList(ReadOnlyAthleteList athletes) throws IOException;

    /**
     * @see #saveAthleteList(ReadOnlyAthleteList)
     */
    void saveAthleteList(ReadOnlyAthleteList athletes, Path filePath) throws IOException;
}
