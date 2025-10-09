package seedu.address.model.contract;

import java.util.Objects;

/** Positive integer amount (e.g., cents or dollars depending on your app spec). */
public class Amount {
    public static final String MESSAGE_CONSTRAINTS = "Amount must be a positive integer.";

    public final int value;

    /**
     * Constructs an {@code Amount} with the specified integer value.
     * The value must be a positive integer.
     *
     * @param value The amount value.
     * @throws IllegalArgumentException if {@code value} is not positive.
     */
    public Amount(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = value;
    }

    /**
     * Returns true if the given string represents a valid positive integer amount.
     *
     * @param test The string to test.
     * @return true if the string is a valid positive integer, false otherwise.
     */
    public static boolean isValidAmount(String test) {
        if (test == null) {
            return false;
        }
        String t = test.trim();
        try {
            int v = Integer.parseInt(t);
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
