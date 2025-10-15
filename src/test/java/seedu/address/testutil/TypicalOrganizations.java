package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.organization.Organization;

/**
 * A utility class containing a list of {@code Organization} objects to be used in tests.
 */
public class TypicalOrganizations {

    public static final Organization NIKE = new OrganizationBuilder()
            .withName("Nike")
            .withPhone("91234567")
            .withEmail("john.smith@nike.com")
            .build();

    public static final Organization ADIDAS = new OrganizationBuilder()
            .withName("Adidas")
            .withPhone("98765432")
            .withEmail("jane.doe@adidas.com")
            .build();

    private TypicalOrganizations() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical organizations.
     */
    public static AddressBook getTypicalAddressBookWithOrganizations() {
        AddressBook ab = new AddressBook();
        for (Organization organization : getTypicalOrganizations()) {
            ab.addOrganization(organization);
        }
        return ab;
    }

    /**
     * Returns a list of typical {@code Organization} objects.
     */
    public static List<Organization> getTypicalOrganizations() {
        return new ArrayList<>(Arrays.asList(NIKE, ADIDAS));
    }
}
