package seedu.address.logic.parser.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {

    /**
     * Constructs a new {@code ParseException} with the specified error message.
     *
     * @param message The detailed error message explaining why parsing failed.
     */
    public ParseException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ParseException} with the specified error message and cause.
     *
     * @param message The detailed error message explaining why parsing failed.
     * @param cause The underlying exception that caused this parse error.
     */
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
