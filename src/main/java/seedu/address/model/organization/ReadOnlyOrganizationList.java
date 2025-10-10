package seedu.address.model.organization;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an organization list.
 */
public interface ReadOnlyOrganizationList {
    /** Returns an unmodifiable view of the organizations list */
    ObservableList<Organization> getOrganizationList();
}
