package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AthleteBook;
import seedu.address.model.athlete.Athlete;

/**
 * A utility class containing a list of {@code Athlete} objects to be used in tests.
 */
public class TypicalAthletes {

    public static final Athlete ALICE = new AthleteBuilder().withName("Alice Pauline")
            .withSport("Swimming").withAge("30").withEmail("alice@example.com")
            .withPhone("94351253").build();
    public static final Athlete BENSON = new AthleteBuilder().withName("Benson Meier")
            .withSport("Pool").withAge("18").withEmail("ben@example.com")
            .withPhone("90808767").build();
    public static final Athlete CARL = new AthleteBuilder().withName("Carl Tan")
            .withSport("Golf").withAge("35").withEmail("carl@example.com")
            .withPhone("81234567").build();
    public static final Athlete DANIEL = new AthleteBuilder().withName("Daniel Soh")
            .withSport("Basketball").withAge("23").withEmail("daniel@example.com")
            .withPhone("87264845").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalAthletes() {} // prevents instantiation

    /**
     * Returns an {@code AthleteBook} with all the typical athletes.
     */
    public static AthleteBook getTypicalAthleteBook() {
        AthleteBook ab = new AthleteBook();
        for (Athlete athlete : getTypicalAthletes()) {
            ab.addAthlete(athlete);
        }
        return ab;
    }

    public static List<Athlete> getTypicalAthletes() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL));
    }
}

