package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedContract.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.contract.TypicalContracts.MESSI_MIAMI;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.athlete.Sport;
import seedu.address.model.contract.Amount;
import seedu.address.model.contract.Date8;

public class JsonAdaptedContractTest {
    private static final JsonAdaptedAthlete VALID_ATHLETE = new JsonAdaptedAthlete(MESSI_MIAMI.getAthlete());
    private static final String VALID_SPORT = MESSI_MIAMI.getSport().value;
    private static final JsonAdaptedOrganization VALID_ORGANIZATION =
            new JsonAdaptedOrganization(MESSI_MIAMI.getOrganization());
    private static final String VALID_START_DATE = MESSI_MIAMI.getStartDate().value;
    private static final String VALID_END_DATE = MESSI_MIAMI.getEndDate().value;
    private static final String VALID_AMOUNT = String.valueOf(MESSI_MIAMI.getAmount().value);

    private static final String INVALID_SPORT = "Football!@#";
    private static final String INVALID_START_DATE = "99999999";
    private static final String INVALID_END_DATE = "00000000";
    private static final String INVALID_AMOUNT = "-1000";

    @Test
    public void toModelType_validContractDetails_returnsContract() throws Exception {
        JsonAdaptedContract contract = new JsonAdaptedContract(MESSI_MIAMI);
        assertEquals(MESSI_MIAMI, contract.toModelType());
    }

    @Test
    public void toModelType_nullAthlete_throwsIllegalValueException() {
        JsonAdaptedContract contract = new JsonAdaptedContract(
                null, VALID_SPORT, VALID_ORGANIZATION, VALID_START_DATE, VALID_END_DATE, VALID_AMOUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Athlete");
        assertThrows(IllegalValueException.class, expectedMessage, contract::toModelType);
    }

    @Test
    public void toModelType_invalidSport_throwsIllegalValueException() {
        JsonAdaptedContract contract = new JsonAdaptedContract(
                VALID_ATHLETE, INVALID_SPORT, VALID_ORGANIZATION, VALID_START_DATE, VALID_END_DATE, VALID_AMOUNT);
        String expectedMessage = Sport.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contract::toModelType);
    }

    @Test
    public void toModelType_nullSport_throwsIllegalValueException() {
        JsonAdaptedContract contract = new JsonAdaptedContract(
                VALID_ATHLETE, null, VALID_ORGANIZATION, VALID_START_DATE, VALID_END_DATE, VALID_AMOUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Sport.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contract::toModelType);
    }

    @Test
    public void toModelType_nullOrganization_throwsIllegalValueException() {
        JsonAdaptedContract contract = new JsonAdaptedContract(
                VALID_ATHLETE, VALID_SPORT, null, VALID_START_DATE, VALID_END_DATE, VALID_AMOUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Organization");
        assertThrows(IllegalValueException.class, expectedMessage, contract::toModelType);
    }

    @Test
    public void toModelType_invalidStartDate_throwsIllegalValueException() {
        JsonAdaptedContract contract = new JsonAdaptedContract(
                VALID_ATHLETE, VALID_SPORT, VALID_ORGANIZATION, INVALID_START_DATE, VALID_END_DATE, VALID_AMOUNT);
        String expectedMessage = Date8.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contract::toModelType);
    }

    @Test
    public void toModelType_nullStartDate_throwsIllegalValueException() {
        JsonAdaptedContract contract = new JsonAdaptedContract(
                VALID_ATHLETE, VALID_SPORT, VALID_ORGANIZATION, null, VALID_END_DATE, VALID_AMOUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "StartDate");
        assertThrows(IllegalValueException.class, expectedMessage, contract::toModelType);
    }

    @Test
    public void toModelType_invalidEndDate_throwsIllegalValueException() {
        JsonAdaptedContract contract = new JsonAdaptedContract(
                VALID_ATHLETE, VALID_SPORT, VALID_ORGANIZATION, VALID_START_DATE, INVALID_END_DATE, VALID_AMOUNT);
        String expectedMessage = Date8.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contract::toModelType);
    }

    @Test
    public void toModelType_nullEndDate_throwsIllegalValueException() {
        JsonAdaptedContract contract = new JsonAdaptedContract(
                VALID_ATHLETE, VALID_SPORT, VALID_ORGANIZATION, VALID_START_DATE, null, VALID_AMOUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "EndDate");
        assertThrows(IllegalValueException.class, expectedMessage, contract::toModelType);
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedContract contract = new JsonAdaptedContract(
                VALID_ATHLETE, VALID_SPORT, VALID_ORGANIZATION, VALID_START_DATE, VALID_END_DATE, INVALID_AMOUNT);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contract::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() {
        JsonAdaptedContract contract = new JsonAdaptedContract(
                VALID_ATHLETE, VALID_SPORT, VALID_ORGANIZATION, VALID_START_DATE, VALID_END_DATE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Amount");
        assertThrows(IllegalValueException.class, expectedMessage, contract::toModelType);
    }

    @Test
    public void toModelType_invalidAthleteInContract_throwsIllegalValueException() {
        // Create an athlete with invalid data
        JsonAdaptedAthlete invalidAthlete = new JsonAdaptedAthlete(null, VALID_SPORT, "30", "91234567",
                "test@example.com");
        JsonAdaptedContract contract = new JsonAdaptedContract(
                invalidAthlete, VALID_SPORT, VALID_ORGANIZATION, VALID_START_DATE, VALID_END_DATE, VALID_AMOUNT);
        assertThrows(IllegalValueException.class, contract::toModelType);
    }

    @Test
    public void toModelType_invalidOrganizationInContract_throwsIllegalValueException() {
        // Create an organization with invalid data
        JsonAdaptedOrganization invalidOrganization = new JsonAdaptedOrganization(
                null, "John Doe", "98765432", "john@nike.com");
        JsonAdaptedContract contract = new JsonAdaptedContract(
                VALID_ATHLETE, VALID_SPORT, invalidOrganization, VALID_START_DATE, VALID_END_DATE, VALID_AMOUNT);
        assertThrows(IllegalValueException.class, contract::toModelType);
    }
}
