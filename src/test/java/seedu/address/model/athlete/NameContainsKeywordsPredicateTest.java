package seedu.address.model.athlete;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AthleteBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        seedu.address.model.athlete.NameContainsKeywordsPredicate firstPredicate =
                new seedu.address.model.athlete.NameContainsKeywordsPredicate(firstPredicateKeywordList);
        seedu.address.model.athlete.NameContainsKeywordsPredicate secondPredicate =
                new seedu.address.model.athlete.NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        seedu.address.model.athlete.NameContainsKeywordsPredicate firstPredicateCopy =
                new seedu.address.model.athlete.NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        seedu.address.model.athlete.NameContainsKeywordsPredicate predicate =
                new seedu.address.model.athlete.NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new AthleteBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new seedu.address.model.athlete.NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new AthleteBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new seedu.address.model.athlete.NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new AthleteBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new seedu.address.model.athlete.NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new AthleteBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        seedu.address.model.athlete.NameContainsKeywordsPredicate predicate =
                new seedu.address.model.athlete.NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new AthleteBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new seedu.address.model.athlete.NameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new AthleteBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email, but does not match name
        predicate = new seedu.address.model.athlete.NameContainsKeywordsPredicate(
                Arrays.asList("91234567", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new AthleteBuilder().withName("Alice").withPhone("91234567")
                .withEmail("alice@email.com").build()));
    }
}
