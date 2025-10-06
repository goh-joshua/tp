package seedu.address.model.organization;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents an Organization in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Organization {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Organization(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both Organizations have the same name.
     * This defines a weaker notion of equality between two Organizations.
     */
    public boolean isSameOrganization(Organization otherOrganization) {
        if (otherOrganization == this) {
            return true;
        }

        return otherOrganization != null
                && otherOrganization.getName().equals(getName());
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

        // instanceof handles nulls
        if (!(other instanceof Organization)) {
            return false;
        }

        Organization otherOrganization = (Organization) other;
        return name.equals(otherOrganization.name)
                && phone.equals(otherOrganization.phone)
                && email.equals(otherOrganization.email)
                && address.equals(otherOrganization.address)
                && tags.equals(otherOrganization.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .toString();
    }

}
