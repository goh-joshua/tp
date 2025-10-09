package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.contract.Contract;
import seedu.address.model.organization.Organization;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Contract} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withContract(Contract contract) {
        addressBook.addContract(contract);
        return this;
    }

    /**
     * Adds a new {@code Athlete} to the {@code AthleteBook} that we are building.
     */
    public AddressBookBuilder withAthlete(Athlete athlete) {
        addressBook.addAthlete(athlete);
        return this;
    }

    /**
     * Adds a new {@code Contract} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withOrganization(Organization organization) {
        addressBook.addOrganization(organization);
        return this;
    }



    public AddressBook build() {
        return addressBook;
    }
}
