package seedu.address.model.organization;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Mutable list of organizations with uniqueness guarantees, exposed as ReadOnlyOrganizationList.
 * Mirrors the organization-specific subset of AddressBook behavior.
 */
public class OrganizationList implements ReadOnlyOrganizationList {

    private final UniqueOrganizationList organizations = new UniqueOrganizationList();

    /**
     * Creates an empty OrganizationList.
     */
    public OrganizationList() {}

    /**
     * Creates an OrganizationList by copying from a read-only source.
     * @param toBeCopied the source list to copy from
     */
    public OrganizationList(ReadOnlyOrganizationList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    // Overwrite operations
    /**
     * Replaces the contents of the organization list with {@code organizations}.
     * Duplicates are not allowed.
     */
    public void setOrganizations(List<Organization> organizations) {
        this.organizations.setOrganizations(organizations);
    }

    /**
     * Resets the existing data of this {@code OrganizationList} with {@code newData}.
     */
    public void resetData(ReadOnlyOrganizationList newData) {
        requireNonNull(newData);
        setOrganizations(newData.getOrganizationList());
    }

    // Organization-level operations
    /**
     * Returns true if an organization with the same identity as {@code organization} exists in the list.
     */
    public boolean hasOrganization(Organization organization) {
        requireNonNull(organization);
        return organizations.contains(organization);
    }

    /**
     * Adds an organization to the list. The organization must not already exist in the list.
     */
    public void addOrganization(Organization organization) {
        organizations.add(organization);
    }

    /**
     * Removes the equivalent organization from the list. The organization must exist in the list.
     */
    public void removeOrganization(Organization organization) {
        organizations.remove(organization);
    }

    /**
     * Returns an unmodifiable view of the internal list of organizations.
     */
    public ObservableList<Organization> getOrganizationList() {
        return organizations.asUnmodifiableObservableList();
    }
}
