package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.contract.Contract;
import seedu.address.model.organization.Organization;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Athlete> getAthleteList();
    ObservableList<Contract> getContractList();
    ObservableList<Organization> getOrganizationList();

}
