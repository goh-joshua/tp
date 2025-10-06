package seedu.address.model.organization;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Organization}'s {@code OrganizationName} matches any of the keywords given.
 */
public class OrganizationNameContainsKeywordsPredicate implements Predicate<Organization> {
    private final List<String> keywords;

    public OrganizationNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Organization Organization) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(Organization.getOrganizationName().fullOrganizationName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrganizationNameContainsKeywordsPredicate)) {
            return false;
        }

        OrganizationNameContainsKeywordsPredicate otherOrganizationNameContainsKeywordsPredicate = (OrganizationNameContainsKeywordsPredicate) other;
        return keywords.equals(otherOrganizationNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
