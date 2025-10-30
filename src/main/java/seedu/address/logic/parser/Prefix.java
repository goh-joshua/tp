package seedu.address.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;

    /**
     * Constructs a {@code Prefix} object with the specified prefix string.
     *
     * @param prefix A non-null string representing the prefix (e.g. {@code "t/"}).
     */
    public Prefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Returns the string representation of this prefix.
     *
     * @return The prefix string (e.g. {@code "t/"}).
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Returns the string form of this prefix.
     *
     * @return The prefix string.
     */
    @Override
    public String toString() {
        return getPrefix();
    }

    /**
     * Returns the hash code value for this prefix.
     *
     * @return The hash code of the prefix string, or {@code 0} if it is null.
     */
    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }

    /**
     * Checks if this prefix is equal to another object.
     * <p>
     * Two {@code Prefix} objects are considered equal if their prefix strings are the same.
     * </p>
     *
     * @param other The object to compare with.
     * @return {@code true} if the other object is a {@code Prefix} with the same prefix string;
     *         {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Prefix)) {
            return false;
        }

        Prefix otherPrefix = (Prefix) other;
        return prefix.equals(otherPrefix.prefix);
    }
}
