package seedu.address.testutil;

import seedu.address.model.organization.Organization;
import seedu.address.model.organization.OrganizationEmail;
import seedu.address.model.organization.OrganizationName;
import seedu.address.model.organization.OrganizationPhone;

/**
 * A utility class to help with building {@code Organization} objects.
 */
public class OrganizationBuilder {

    public static final String DEFAULT_NAME = "Nike";
    public static final String DEFAULT_PHONE = "98765432";
    public static final String DEFAULT_EMAIL = "john.doe@nike.com";

    private OrganizationName name;
    private OrganizationPhone phone;
    private OrganizationEmail email;

    /**
     * Creates a {@code OrganizationBuilder} with the default details.
     */
    public OrganizationBuilder() {
        name = new OrganizationName(DEFAULT_NAME);
        phone = new OrganizationPhone(DEFAULT_PHONE);
        email = new OrganizationEmail(DEFAULT_EMAIL);
    }

    /**
     * Initializes the OrganizationBuilder with the data of {@code organizationToCopy}.
     */
    public OrganizationBuilder(Organization organizationToCopy) {
        name = organizationToCopy.getName();
        phone = organizationToCopy.getPhone();
        email = organizationToCopy.getEmail();
    }

    /**
     * Sets the {@code OrganizationName} of the {@code Organization} that we are building.
     */
    public OrganizationBuilder withName(String name) {
        this.name = new OrganizationName(name);
        return this;
    }

    /**
     * Sets the {@code OrganizationPhone} of the {@code Organization} that we are building.
     */
    public OrganizationBuilder withPhone(String phone) {
        this.phone = new OrganizationPhone(phone);
        return this;
    }

    /**
     * Sets the {@code OrganizationEmail} of the {@code Organization} that we are building.
     */
    public OrganizationBuilder withEmail(String email) {
        this.email = new OrganizationEmail(email);
        return this;
    }

    /**
     * Builds and returns the {@code Organization}.
     */
    public Organization build() {
        return new Organization(name, phone, email);
    }
}
