package seedu.address.testutil.athlete;

import seedu.address.model.athlete.Age;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.Email;
import seedu.address.model.athlete.Name;
import seedu.address.model.athlete.Phone;
import seedu.address.model.athlete.Sport;

/**
 * A utility class to help with building Athlete objects.
 */
public class AthleteBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_SPORT = "Swimming";
    public static final String DEFAULT_AGE = "20";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";

    private Name name;
    private Sport sport;
    private Age age;
    private Phone phone;
    private Email email;


    /**
     * Creates a {@code AthleteBuilder} with the default details.
     */
    public AthleteBuilder() {
        name = new Name(DEFAULT_NAME);
        sport = new Sport(DEFAULT_SPORT);
        age = new Age(DEFAULT_AGE);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
    }

    /**
     * Initializes the AthleteBuilder with the data of {@code athleteToCopy}.
     */
    public AthleteBuilder(Athlete athleteToCopyToCopy) {
        name = athleteToCopyToCopy.getName();
        sport = athleteToCopyToCopy.getSport();
        age = athleteToCopyToCopy.getAge();
        phone = athleteToCopyToCopy.getPhone();
        email = athleteToCopyToCopy.getEmail();
    }

    /**
     * Sets the {@code Name} of the {@code Athlete} that we are building.
     */
    public AthleteBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Sport} of the {@code Athlete} that we are building.
     */
    public AthleteBuilder withSport(String sport) {
        this.sport = new Sport(sport);
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code Athlete} that we are building.
     */
    public AthleteBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Athlete} that we are building.
     */
    public AthleteBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Athlete} that we are building.
     */
    public AthleteBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Athlete build() {
        return new Athlete(name, sport, age, phone, email);
    }

}
