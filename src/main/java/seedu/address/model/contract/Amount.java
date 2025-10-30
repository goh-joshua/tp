package seedu.address.model.contract;

import java.util.Objects;

/**
 * Represents a positive long integer amount in a Contract.
 * <p>
 * Guarantees: immutable; value is always positive and within the range of a Java {@code long}.
 */
public class Amount {
    public static final String MESSAGE_CONSTRAINTS =
            "Error: Amount should be a numeric integer between 1 and 9,223,372,036,854,775,807(inclusive). "
                    + "Do not include currency symbols or commas.";

    public final long value;

    /**
     * Constructs an {@code Amount} with the specified long integer value.
     * The value must be a positive integer.
     *
     * @param value The amount value.
     * @throws IllegalArgumentException if {@code value} is not positive.
     */
    public Amount(long value) {
        if (value <= 0) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = value;
    }

    /**
     * Returns true if the given string represents a valid positive long integer amount.
     *
     * @param test The string to test.
     * @return true if the string is a valid positive long integer, false otherwise.
     */
    public static boolean isValidAmount(String test) {
        if (test == null) {
            return false;
        }
        String t = test.trim();
        try {
            long v = Long.parseLong(t);
            return v > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override public String toString() {
        return String.valueOf(value);
    }
    @Override public boolean equals(Object o) {
        return o instanceof Amount && value == ((Amount) o).value;
    }
    @Override public int hashCode() {
        return Objects.hash(value);
    }
}
