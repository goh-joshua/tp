package seedu.address.model.contract.exceptions;

/** Signals that the operation will result in duplicate contracts. */
public class DuplicateContractException extends RuntimeException {
    /**
     * Constructs a new {@code DuplicateContractException} with a default message.
     */
    public DuplicateContractException() {
        super("Operation would result in duplicate contracts");
    }
}
