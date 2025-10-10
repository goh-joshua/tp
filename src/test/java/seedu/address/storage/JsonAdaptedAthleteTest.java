package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAthlete.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.athlete.TypicalAthletes.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.athlete.Age;
import seedu.address.model.athlete.Email;
import seedu.address.model.athlete.Name;
import seedu.address.model.athlete.Phone;
import seedu.address.model.athlete.Sport;

public class JsonAdaptedAthleteTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_SPORT = "Running!@#";
    private static final String INVALID_AGE = "999";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";

    private static final String VALID_NAME = ALICE.getName().fullName;
    private static final String VALID_SPORT = ALICE.getSport().value;
    private static final String VALID_AGE = ALICE.getAge().value;
    private static final String VALID_PHONE = ALICE.getPhone().value;
    private static final String VALID_EMAIL = ALICE.getEmail().value;

    @Test
    public void toModelType_validAthleteDetails_returnsAthlete() throws Exception {
        JsonAdaptedAthlete athlete = new JsonAdaptedAthlete(ALICE);
        assertEquals(ALICE, athlete.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedAthlete athlete =
                new JsonAdaptedAthlete(INVALID_NAME, VALID_SPORT, VALID_AGE, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, athlete::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedAthlete athlete =
                new JsonAdaptedAthlete(null, VALID_SPORT, VALID_AGE, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, athlete::toModelType);
    }

    @Test
    public void toModelType_invalidSport_throwsIllegalValueException() {
        JsonAdaptedAthlete athlete =
                new JsonAdaptedAthlete(VALID_NAME, INVALID_SPORT, VALID_AGE, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = Sport.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, athlete::toModelType);
    }

    @Test
    public void toModelType_nullSport_throwsIllegalValueException() {
        JsonAdaptedAthlete athlete =
                new JsonAdaptedAthlete(VALID_NAME, null, VALID_AGE, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Sport.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, athlete::toModelType);
    }

    @Test
    public void toModelType_invalidAge_throwsIllegalValueException() {
        JsonAdaptedAthlete athlete =
                new JsonAdaptedAthlete(VALID_NAME, VALID_SPORT, INVALID_AGE, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = Age.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, athlete::toModelType);
    }

    @Test
    public void toModelType_nullAge_throwsIllegalValueException() {
        JsonAdaptedAthlete athlete =
                new JsonAdaptedAthlete(VALID_NAME, VALID_SPORT, null, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, athlete::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedAthlete athlete =
                new JsonAdaptedAthlete(VALID_NAME, VALID_SPORT, VALID_AGE, INVALID_PHONE, VALID_EMAIL);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, athlete::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedAthlete athlete =
                new JsonAdaptedAthlete(VALID_NAME, VALID_SPORT, VALID_AGE, null, VALID_EMAIL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, athlete::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedAthlete athlete =
                new JsonAdaptedAthlete(VALID_NAME, VALID_SPORT, VALID_AGE, VALID_PHONE, INVALID_EMAIL);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, athlete::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedAthlete athlete =
                new JsonAdaptedAthlete(VALID_NAME, VALID_SPORT, VALID_AGE, VALID_PHONE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, athlete::toModelType);
    }
}
