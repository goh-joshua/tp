package seedu.address.model.organization;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.organization.exceptions.DuplicateOrganizationException;
import seedu.address.model.organization.exceptions.OrganizationNotFoundException;

/**
 * A list of Organizations that enforces uniqueness between its elements and does not allow nulls.
 * An Organizationation is considered unique by comparing using {@code Organization#isSameOrganization(Organization)}. As such, adding and updating of
 * Organizations uses Organization#isSameOrganization(Organization) for equality so as to ensure that the Organization being added or updated is
 * unique in terms of identity in the UniqueOrganizationList. However, the removal of an Organization uses Organization#equals(Object) so
 * as to ensure that the Organization with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Organization#isSameOrganization(Organization)
 */
public class UniqueOrganizationList implements Iterable<Organization> {

    private final ObservableList<Organization> internalList = FXCollections.observableArrayList();
    private final ObservableList<Organization> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Organization as the given argument.
     */
    public boolean contains(Organization toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameOrganization);
    }

    /**
     * Adds an Organization to the list.
     * The Organization must not already exist in the list.
     */
    public void add(Organization toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateOrganizationException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Organization {@code target} in the list with {@code editedOrganization}.
     * {@code target} must exist in the list.
     * The Organization identity of {@code editedOrganization} must not be the same as another existing Organization in the list.
     */
    public void setOrganization(Organization target, Organization editedOrganization) {
        requireAllNonNull(target, editedOrganization);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new OrganizationNotFoundException();
        }

        if (!target.isSameOrganization(editedOrganization) && contains(editedOrganization)) {
            throw new DuplicateOrganizationException();
        }

        internalList.set(index, editedOrganization);
    }

    /**
     * Removes the equivalent Organization from the list.
     * The Organization must exist in the list.
     */
    public void remove(Organization toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new OrganizationNotFoundException();
        }
    }

    public void setOrganizations(UniqueOrganizationList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Organizations}.
     * {@code Organizations} must not contain duplicate Organizations.
     */
    public void setOrganizations(List<Organization> Organizations) {
        requireAllNonNull(Organizations);
        if (!OrganizationsAreUnique(Organizations)) {
            throw new DuplicateOrganizationException();
        }

        internalList.setAll(Organizations);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Organization> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Organization> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueOrganizationList)) {
            return false;
        }

        UniqueOrganizationList otherUniqueOrganizationList = (UniqueOrganizationList) other;
        return internalList.equals(otherUniqueOrganizationList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code Organizations} contains only unique Organizations.
     */
    private boolean OrganizationsAreUnique(List<Organization> Organizations) {
        for (int i = 0; i < Organizations.size() - 1; i++) {
            for (int j = i + 1; j < Organizations.size(); j++) {
                if (Organizations.get(i).isSameOrganization(Organizations.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
