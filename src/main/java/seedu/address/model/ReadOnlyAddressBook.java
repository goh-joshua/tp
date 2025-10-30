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
     * Returns an unmodifiable view of the list of athletes.
     * The list will not contain any duplicate athletes.
     *
     * @return unmodifiable list of athletes
     */
    ObservableList<Athlete> getAthleteList();

    /**
     * Returns an unmodifiable view of the list of contracts.
     * The list will not contain any duplicate contracts.
     *
     * @return unmodifiable list of contracts
     */
    ObservableList<Contract> getContractList();

    /**
     * Returns an unmodifiable view of the list of organizations.
     * The list will not contain any duplicate organizations.
     *
     * @return unmodifiable list of organizations
     */
    ObservableList<Organization> getOrganizationList();

}
