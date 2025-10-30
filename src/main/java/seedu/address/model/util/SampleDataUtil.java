package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.athlete.AthleteList;
import seedu.address.model.athlete.ReadOnlyAthleteList;
import seedu.address.model.contract.ContractList;
import seedu.address.model.contract.ReadOnlyContractList;
import seedu.address.model.organization.OrganizationList;
import seedu.address.model.organization.ReadOnlyOrganizationList;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    /**
     * Returns an empty address book  for initializing the model when no data is present.
     */
    public static ReadOnlyAddressBook getSampleAddressBook() {
        return new AddressBook();
    }

    /**
     * Returns an empty athlete list for initializing the model when no athlete data is present.
     */
    public static ReadOnlyAthleteList getEmptyAthleteList() {
        return new AthleteList();
    }

    /**
     * Returns an empty contract list for initializing the model when no contract data is present.
     */
    public static ReadOnlyContractList getEmptyContractList() {
        return new ContractList();
    }

    /**
     * Returns an empty organization list for initializing the model when no organization data is present.
     */
    public static ReadOnlyOrganizationList getEmptyOrganizationList() {
        return new OrganizationList();
    }

}
