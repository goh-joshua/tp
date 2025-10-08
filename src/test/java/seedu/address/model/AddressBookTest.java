package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.Sport;
import seedu.address.model.athlete.exceptions.DuplicateAthleteException;
import seedu.address.model.contract.Amount;
import seedu.address.model.contract.Contract;
import seedu.address.model.contract.Date8;
import seedu.address.model.contract.exceptions.DuplicateContractException;
import seedu.address.model.organization.Organization;
import seedu.address.model.organization.exceptions.DuplicateOrganizationException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.OrganizationBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.athlete.AthleteBuilder;

/**
 * Tests for {@link AddressBook}.
 */
public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        Person editedAlice = new PersonBuilder(ALICE)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(
                newPersons, List.of(), List.of(), List.of());
        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    // -------- Persons --------
    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    // -------- Organizations --------
    @Test
    public void hasOrganization_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasOrganization(null));
    }

    @Test
    public void hasOrganization_notPresent_returnsFalse() {
        Organization nike = new OrganizationBuilder().withName("Nike").build();
        assertFalse(addressBook.hasOrganization(nike));
    }

    @Test
    public void addAndHasOrganization_present_returnsTrue() {
        Organization nike = new OrganizationBuilder().withName("Nike").build();
        addressBook.addOrganization(nike);
        assertTrue(addressBook.hasOrganization(nike));
    }

    @Test
    public void getOrganizationList_modify_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getOrganizationList().remove(0));
    }

    @Test
    public void resetData_withDuplicateOrganizations_throwsDuplicateOrganizationException() {
        Organization org = new OrganizationBuilder().withName("Acme FC").build();
        Organization dup = new OrganizationBuilder().withName("Acme FC").build(); // same identity
        AddressBookStub stub = new AddressBookStub(
                List.of(), List.of(), Arrays.asList(org, dup), List.of());
        assertThrows(DuplicateOrganizationException.class, () -> addressBook.resetData(stub));
    }

    // -------- Athletes --------
    @Test
    public void hasAthlete_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasAthlete(null));
    }

    @Test
    public void hasAthlete_notPresent_returnsFalse() {
        Athlete a = new AthleteBuilder().withName("Test Athlete").build();
        assertFalse(addressBook.hasAthlete(a));
    }

    @Test
    public void addAndHasAthlete_present_returnsTrue() {
        Athlete a = new AthleteBuilder().withName("Test Athlete").build();
        addressBook.addAthlete(a);
        assertTrue(addressBook.hasAthlete(a));
    }

    @Test
    public void getAthleteList_modify_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getAthleteList().remove(0));
    }

    @Test
    public void resetData_withDuplicateAthletes_throwsDuplicateAthleteException() {
        Athlete a1 = new AthleteBuilder().withName("Same Name").build();
        Athlete a2 = new AthleteBuilder().withName("Same Name").build(); // same identity
        AddressBookStub stub = new AddressBookStub(
                List.of(), Arrays.asList(a1, a2), List.of(), List.of());
        assertThrows(DuplicateAthleteException.class, () -> addressBook.resetData(stub));
    }

    // -------- Contracts --------
    @Test
    public void hasContract_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasContract(null));
    }

    @Test
    public void addHasRemoveContract_roundTrip() {
        Athlete athlete = new AthleteBuilder().withName("Lionel Messi").build();
        Organization org = new OrganizationBuilder().withName("Inter Miami").build();
        // Create a minimal Contract (adjust if your ctor differs)
        Contract c = new Contract(
                athlete,
                new Sport("Football"),
                org,
                new Date8("01012024"),
                new Date8("01012025"),
                new Amount(5_000_000));

        // Must add the referenced entities first, though AddressBook doesn't enforce it
        addressBook.addAthlete(athlete);
        addressBook.addOrganization(org);

        assertFalse(addressBook.hasContract(c));
        addressBook.addContract(c);
        assertTrue(addressBook.hasContract(c));

        // list must be unmodifiable
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getContractList().remove(0));

        // remove should succeed
        addressBook.removeContract(c);
        assertFalse(addressBook.hasContract(c));
    }

    @Test
    public void resetData_withDuplicateContracts_throwsDuplicateContractException() {
        Athlete athlete = new AthleteBuilder().withName("A").build();
        Organization org = new OrganizationBuilder().withName("O").build();
        Contract c1 = new Contract(
                athlete,
                new Sport("Football"),
                org,
                new Date8("01012024"),
                new Date8("01012025"),
                new Amount(1));
        Contract c2 = new Contract(
                athlete,
                new Sport("Football"),
                org,
                new Date8("01012024"),
                new Date8("01012025"),
                new Amount(1)); // same identity as c1

        AddressBookStub stub = new AddressBookStub(
                List.of(), List.of(athlete), List.of(org), Arrays.asList(c1, c2));
        assertThrows(DuplicateContractException.class, () -> addressBook.resetData(stub));
    }

    // -------- toString / equals / hashCode --------
    @Test
    public void toStringMethod() {
        String actual = addressBook.toString();
        assertTrue(actual.contains("persons="), "toString() should include persons=");
        assertTrue(actual.contains("organizations="), "toString() should include organizations=");
        assertTrue(actual.contains("athletes="), "toString() should include athletes=");
        assertTrue(actual.contains("contracts="), "toString() should include contracts=");
    }

    @Test
    public void equalsAndHashCode_consistency() {
        AddressBook a = new AddressBook();
        AddressBook b = new AddressBook();
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());

        Athlete ath = new AthleteBuilder().withName("X").build();
        a.addAthlete(ath);
        assertFalse(a.equals(b));
    }

    /**
     * A stub ReadOnlyAddressBook whose lists can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Athlete> athletes = FXCollections.observableArrayList();
        private final ObservableList<Organization> organizations = FXCollections.observableArrayList();
        private final ObservableList<Contract> contracts = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons,
                        Collection<Athlete> athletes,
                        Collection<Organization> organizations,
                        Collection<Contract> contracts) {
            this.persons.setAll(persons);
            this.athletes.setAll(athletes);
            this.organizations.setAll(organizations);
            this.contracts.setAll(contracts);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Athlete> getAthleteList() {
            return athletes;
        }

        @Override
        public ObservableList<Contract> getContractList() {
            return contracts;
        }

        @Override
        public ObservableList<Organization> getOrganizationList() {
            return organizations;
        }
    }
}
