package seedu.address.model.athlete;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that an {@code Athlete}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Athlete> {
    private final List<String> keywords;

    /**
     * Constructs a {@code NameContainsKeywordsPredicate} with the specified list of keywords.
     *
     * @param keywords A list of keywords to test against an athlete's name.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests whether the athlete's name contains any of the given keywords.
     * Matching is case-insensitive and checks for full-word matches.
     *
     * @param athlete The {@code Athlete} to test.
     * @return {@code true} if the athlete's name contains any keyword, {@code false} otherwise.
     */
    @Override
    public boolean test(Athlete athlete) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(athlete.getName().fullName, keyword));
    }

    /**
     * Returns true if both predicates are equal — i.e., have the same list of keywords.
     *
     * @param other The object to compare with.
     * @return {@code true} if both predicates are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        NameContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (NameContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    /**
     * Returns a string representation of this predicate for debugging purposes.
     *
     * @return A string representation containing the list of keywords.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
