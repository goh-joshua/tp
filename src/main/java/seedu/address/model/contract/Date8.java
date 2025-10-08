package seedu.address.model.contract;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/** DDMMYYYY date with strict validation (e.g., 01012025). */
public class Date8 {
    public static final String MESSAGE_CONSTRAINTS =
            "Date must be DDMMYYYY and a real calendar date (e.g., 01012025).";
    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("ddMMuuuu").withResolverStyle(ResolverStyle.STRICT);

    public final String value;

    public Date8(String value) {
        requireNonNull(value);
        String v = value.trim();
        if (v.length() != 8 || !v.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        try {
            LocalDate.parse(v, FMT); // throws if invalid
        } catch (Exception e) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = v;
    }

    public static boolean isValidDate8(String test) {
        if (test == null) return false;
        String t = test.trim();
        if (t.length() != 8 || !t.chars().allMatch(Character::isDigit)) return false;
        try {
            LocalDate.parse(t, FMT);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public LocalDate toLocalDate() {
        return LocalDate.parse(value, FMT);
    }

    @Override public String toString() { return value; }
    @Override public boolean equals(Object o) { return o instanceof Date8 && value.equals(((Date8) o).value); }
    @Override public int hashCode() { return Objects.hash(value); }
}
