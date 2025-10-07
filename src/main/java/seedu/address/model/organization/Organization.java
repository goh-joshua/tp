package seedu.address.model.organization;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents an Organization in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Organization {

    // Identity fields
    private final OrganizationName name;
    private final OrganizationContactName contactName;
    private final OrganizationPhone phone;
    private final OrganizationEmail email;

    /**
     * Every field must be present and not null.
     */
    public Organization(OrganizationName name, OrganizationContactName contactName,
                        OrganizationPhone phone, OrganizationEmail email) {
        requireAllNonNull(name, contactName, phone, email);
        this.name = name;
        this.contactName = contactName;
        this.phone = phone;
        this.email = email;
    }

    public OrganizationName getName() {
        return name;
    }

    public OrganizationContactName getContactName() {
        return contactName;
    }

    public OrganizationPhone getPhone() {
        return phone;
    }

    public OrganizationEmail getEmail() {
        return email;
    }

    /**
     * Returns true if both Organizations have the same name and contact person.
     * This defines a weaker notion of equality between two Organizations.
     */
    public boolean isSameOrganization(Organization otherOrganization) {
        if (otherOrganization == this) {
            return true;
        }
        return otherOrganization != null
                && otherOrganization.getName().equals(name)
                && otherOrganization.getContactName().equals(contactName);
    }

    /**
     * Returns true if both Organizations have the same identity and data fields.
     * This defines a stronger notion of equality between two Organizations.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Organization)) {
            return false;
        }

        Organization otherOrg = (Organization) other;
        return name.equals(otherOrg.name)
                && contactName.equals(otherOrg.contactName)
                && phone.equals(otherOrg.phone)
                && email.equals(otherOrg.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, contactName, phone, email);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("contactName", contactName)
                .add("phone", phone)
                .add("email", email)
                .toString();
    }
}
