package seedu.address.model.organization.exceptions;

/**
 * Signals that the operation will result in duplicate Organizations (Organizations are considered duplicates if they have the same
 * identity).
 */
public class DuplicateOrganizationException extends RuntimeException {
    public DuplicateOrganizationException() {
        super("Operation would result in duplicate Organizations");
    }
}
