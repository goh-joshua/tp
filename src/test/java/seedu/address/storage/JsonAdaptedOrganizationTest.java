package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedOrganization.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.organization.Organization;
import seedu.address.model.organization.OrganizationContactName;
import seedu.address.model.organization.OrganizationEmail;
import seedu.address.model.organization.OrganizationName;
import seedu.address.model.organization.OrganizationPhone;
import seedu.address.testutil.OrganizationBuilder;

public class JsonAdaptedOrganizationTest {
    private static final String INVALID_NAME = "N!ke@";
    private static final String INVALID_CONTACT_NAME = "J@hn D0e!";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";

    private static final Organization VALID_ORGANIZATION = new OrganizationBuilder().build();
    private static final String VALID_NAME = VALID_ORGANIZATION.getName().fullOrganizationName;
    private static final String VALID_CONTACT_NAME = VALID_ORGANIZATION.getContactName().fullName;
    private static final String VALID_PHONE = VALID_ORGANIZATION.getPhone().value;
    private static final String VALID_EMAIL = VALID_ORGANIZATION.getEmail().value;

    @Test
    public void toModelType_validOrganizationDetails_returnsOrganization() throws Exception {
        JsonAdaptedOrganization organization = new JsonAdaptedOrganization(VALID_ORGANIZATION);
        assertEquals(VALID_ORGANIZATION, organization.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedOrganization organization =
                new JsonAdaptedOrganization(INVALID_NAME, VALID_CONTACT_NAME, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = OrganizationName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, organization::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedOrganization organization =
                new JsonAdaptedOrganization(null, VALID_CONTACT_NAME, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                OrganizationName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, organization::toModelType);
    }

    @Test
    public void toModelType_invalidContactName_throwsIllegalValueException() {
        JsonAdaptedOrganization organization =
                new JsonAdaptedOrganization(VALID_NAME, INVALID_CONTACT_NAME, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = OrganizationContactName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, organization::toModelType);
    }

    @Test
    public void toModelType_nullContactName_throwsIllegalValueException() {
        JsonAdaptedOrganization organization =
                new JsonAdaptedOrganization(VALID_NAME, null, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                OrganizationContactName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, organization::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedOrganization organization =
                new JsonAdaptedOrganization(VALID_NAME, VALID_CONTACT_NAME, INVALID_PHONE, VALID_EMAIL);
        String expectedMessage = OrganizationPhone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, organization::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedOrganization organization =
                new JsonAdaptedOrganization(VALID_NAME, VALID_CONTACT_NAME, null, VALID_EMAIL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                OrganizationPhone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, organization::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedOrganization organization =
                new JsonAdaptedOrganization(VALID_NAME, VALID_CONTACT_NAME, VALID_PHONE, INVALID_EMAIL);
        String expectedMessage = OrganizationEmail.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, organization::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedOrganization organization =
                new JsonAdaptedOrganization(VALID_NAME, VALID_CONTACT_NAME, VALID_PHONE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                OrganizationEmail.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, organization::toModelType);
    }
}
