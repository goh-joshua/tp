package seedu.address.model.athlete;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SportTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.athlete.Sport(null));
    }

    @Test
    public void constructor_invalidSport_throwsIllegalArgumentException() {
        String invalidSport = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.address.model.athlete.Sport(invalidSport));
    }

    @Test
    public void isValidSport() {
        // null sport
        assertThrows(NullPointerException.class, () -> seedu.address.model.athlete.Sport.isValidSport(null));

        // blank email
        assertFalse(seedu.address.model.athlete.Sport.isValidSport("")); // empty string
        assertFalse(seedu.address.model.athlete.Sport.isValidSport(" ")); // spaces only

        // invalid parts
        assertFalse(seedu.address.model.athlete.Sport.isValidSport(" Basketball")); // leading space
        assertFalse(seedu.address.model.athlete.Sport.isValidSport("Basket-ball")); // dash
        assertFalse(seedu.address.model.athlete.Sport.isValidSport("Basket_Ball")); // underscore
        assertFalse(seedu.address.model.athlete.Sport.isValidSport("Pool 2")); // numbers

        // valid sport
        assertTrue(seedu.address.model.athlete.Sport.isValidSport("Basketball"));
        assertTrue(seedu.address.model.athlete.Sport.isValidSport("Hip hop"));
        assertTrue(seedu.address.model.athlete.Sport.isValidSport("Volleyball"));

    }

    @Test
    public void equals() {
        seedu.address.model.athlete.Sport sport = new seedu.address.model.athlete.Sport("Golf");

        // same values -> returns true
        assertTrue(sport.equals(new seedu.address.model.athlete.Sport("Golf")));

        // sport differs in casing -> returns true
        assertTrue(sport.equals(new seedu.address.model.athlete.Sport("golf")));

        // same object -> returns true
        assertTrue(sport.equals(sport));

        // null -> returns false
        assertFalse(sport.equals(null));

        // different types -> returns false
        assertFalse(sport.equals(5.0f));

        // different values -> returns false
        assertFalse(sport.equals(new Sport("Basketball")));
    }
}
