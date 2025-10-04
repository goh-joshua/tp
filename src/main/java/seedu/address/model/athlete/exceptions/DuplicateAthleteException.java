package seedu.address.model.athlete.exceptions;

/**
 * Signals that the operation will result in duplicate Athletes
 */
public class DuplicateAthleteException extends RuntimeException {
    public DuplicateAthleteException() {
        super("Operation would result in duplicate athletes");
    }
}
