package seedu.address.testutil;

import seedu.address.model.AthleteBook;
import seedu.address.model.athlete.Athlete;

/**
 * A utility class to help with building Athletebook objects.
 */
public class AthleteBookBuilder {

    private AthleteBook athleteBook;

    public AthleteBookBuilder() {
        athleteBook = new AthleteBook();
    }

    public AthleteBookBuilder(AthleteBook athleteBook) {
        this.athleteBook = athleteBook;
    }

    /**
     * Adds a new {@code Athlete} to the {@code AthleteBook} that we are building.
     */
    public AthleteBookBuilder withAthlete(Athlete athlete) {
        athleteBook.addAthlete(athlete);
        return this;
    }

    public AthleteBook build() {
        return athleteBook;
    }
}
