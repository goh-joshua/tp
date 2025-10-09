package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.athlete.Age;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.Email;
import seedu.address.model.athlete.Name;
import seedu.address.model.athlete.Phone;
import seedu.address.model.athlete.Sport;

/** Jackson-friendly version of {@link Athlete}. */
class JsonAdaptedAthlete {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Athlete's %s field is missing!";

    private final String name;
    private final String sport;
    private final String age;
    private final String phone;
    private final String email;

    @JsonCreator
    public JsonAdaptedAthlete(@JsonProperty("name") String name,
                              @JsonProperty("sport") String sport,
                              @JsonProperty("age") String age,
                              @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email) {
        this.name = name;
        this.sport = sport;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    /** Converts a given {@code Athlete} into this class for Jackson use. */
    public JsonAdaptedAthlete(Athlete source) {
        this.name = source.getName().fullName;
        this.sport = source.getSport().value;
        this.age = source.getAge().value;
        this.phone = source.getPhone().value;
        this.email = source.getEmail().value;
    }

    /** Converts this Jackson-friendly adapted object into the model's {@code Athlete}. */
    public Athlete toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (sport == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Sport.class.getSimpleName()));
        }
        if (!Sport.isValidSport(sport)) {
            throw new IllegalValueException(Sport.MESSAGE_CONSTRAINTS);
        }
        final Sport modelSport = new Sport(sport);

        if (age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName()));
        }
        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(Age.MESSAGE_CONSTRAINTS);
        }
        final Age modelAge = new Age(age);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        return new Athlete(modelName, modelSport, modelAge, modelPhone, modelEmail);
    }

    // equals and hashCode methods removed as per requirements
}
