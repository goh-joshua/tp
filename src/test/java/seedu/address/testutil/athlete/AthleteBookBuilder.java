package seedu.address.testutil.athlete;

import seedu.address.model.AddressBook;
import seedu.address.model.athlete.Athlete;

/**
 * A utility class to help with building Athletebook objects.
 */
public class AthleteBookBuilder {

    private AddressBook addressBook;

    public AthleteBookBuilder() {
        addressBook = new AddressBook();
    }

    public AthleteBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Athlete} to the {@code AthleteBook} that we are building.
     */
    public AthleteBookBuilder withAthlete(Athlete athlete) {
        addressBook.addAthlete(athlete);
        return this;
    }

    public AddressBook build() {
        return this.addressBook;
    }
}
