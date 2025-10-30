package seedu.address.commons.exceptions;

/**
 * Represents an error during loading of data from a file.
 */
public class DataLoadingException extends Exception {
    /**
     * Constructs a new {@code DataLoadingException} with the specified cause.
     *
     * @param cause The exception that caused the data loading to fail.
     */
    public DataLoadingException(Exception cause) {
        super(cause);
    }

}
