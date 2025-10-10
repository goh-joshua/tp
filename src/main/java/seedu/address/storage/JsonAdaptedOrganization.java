package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.organization.Organization;
import seedu.address.model.organization.OrganizationContactName;
import seedu.address.model.organization.OrganizationEmail;
import seedu.address.model.organization.OrganizationName;
import seedu.address.model.organization.OrganizationPhone;

/** Jackson-friendly version of {@link Organization}. */
class JsonAdaptedOrganization {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Organization's %s field is missing!";

    private final String name;
    private final String contactName;
    private final String phone;
    private final String email;

    @JsonCreator
    public JsonAdaptedOrganization(
            @JsonProperty("name") String name,
            @JsonProperty("contactName") String contactName,
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email) {
        this.name = name;
        this.contactName = contactName;
        this.phone = phone;
        this.email = email;
    }

    /** Converts a given {@code Organization} into this class for Jackson use. */
    public JsonAdaptedOrganization(Organization source) {
        this.name = source.getName().fullOrganizationName;
        this.contactName = source.getContactName().fullName;
        this.phone = source.getPhone().value;
        this.email = source.getEmail().value;
    }

    /** Converts this Jackson-friendly adapted object into the model's {@code Organization}. */
    public Organization toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, OrganizationName.class.getSimpleName()));
        }
        if (!OrganizationName.isValidName(name)) {
            throw new IllegalValueException(OrganizationName.MESSAGE_CONSTRAINTS);
        }
        final OrganizationName modelName = new OrganizationName(name);

        if (contactName == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, OrganizationContactName.class.getSimpleName()));
        }
        if (!OrganizationContactName.isValidName(contactName)) {
            throw new IllegalValueException(OrganizationContactName.MESSAGE_CONSTRAINTS);
        }
        final OrganizationContactName modelContactName = new OrganizationContactName(contactName);

        if (phone == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, OrganizationPhone.class.getSimpleName()));
        }
        if (!OrganizationPhone.isValidPhone(phone)) {
            throw new IllegalValueException(OrganizationPhone.MESSAGE_CONSTRAINTS);
        }
        final OrganizationPhone modelPhone = new OrganizationPhone(phone);

        if (email == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, OrganizationEmail.class.getSimpleName()));
        }
        if (!OrganizationEmail.isValidEmail(email)) {
            throw new IllegalValueException(OrganizationEmail.MESSAGE_CONSTRAINTS);
        }
        final OrganizationEmail modelEmail = new OrganizationEmail(email);

        return new Organization(modelName, modelContactName, modelPhone, modelEmail);
    }
}
