package seedu.address.logic.commands.contract;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.Sport;
import seedu.address.model.contract.Amount;
import seedu.address.model.contract.Contract;
import seedu.address.model.contract.Date8;
import seedu.address.model.organization.Organization;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.OrganizationBuilder;
import seedu.address.testutil.athlete.AthleteBuilder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class AddContractCommandTest {

    private static final Name ATHLETE_NAME = new Name("Lionel Messi");
    private static final Sport SPORT = new Sport("Football");
    private static final Name ORG_NAME = new Name("Inter Miami");
    private static final Date8 START = new Date8("01012024");
    private static final Date8 END = new Date8("01012025");
    private static final Amount AMT = new Amount(5_000_000);

    private Athlete validAthlete() {
        return new AthleteBuilder().withName(ATHLETE_NAME.fullName).build();
    }

    private Organization validOrganization() {
        return new OrganizationBuilder().withName(ORG_NAME.fullName).build();
    }

    // ---------- constructor null checks ----------
    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> new AddContractCommand(null, SPORT, ORG_NAME, START, END, AMT));
        assertThrows(NullPointerException.class,
                () -> new AddContractCommand(ATHLETE_NAME, null, ORG_NAME, START, END, AMT));
        assertThrows(NullPointerException.class,
                () -> new AddContractCommand(ATHLETE_NAME, SPORT, null, START, END, AMT));
        assertThrows(NullPointerException.class,
                () -> new AddContractCommand(ATHLETE_NAME, SPORT, ORG_NAME, null, END, AMT));
        assertThrows(NullPointerException.class,
                () -> new AddContractCommand(ATHLETE_NAME, SPORT, ORG_NAME, START, null, AMT));
        assertThrows(NullPointerException.class,
                () -> new AddContractCommand(ATHLETE_NAME, SPORT, ORG_NAME, START, END, null));
    }

    @Test
    public void execute_athleteMissing_throwsCommandException() {
        ModelStubAcceptingContractAdded modelStub = new ModelStubAcceptingContractAdded();
        // Only org present
        modelStub.addOrganization(validOrganization());

        AddContractCommand cmd = new AddContractCommand(ATHLETE_NAME, SPORT, ORG_NAME, START, END, AMT);
        assertThrows(CommandException.class, () -> cmd.execute(modelStub));
        assertTrue(modelStub.contractsAdded.isEmpty());
    }

    @Test
    public void execute_organizationMissing_throwsCommandException() {
        ModelStubAcceptingContractAdded modelStub = new ModelStubAcceptingContractAdded();
        // Only athlete present
        modelStub.addAthlete(validAthlete());

        AddContractCommand cmd = new AddContractCommand(ATHLETE_NAME, SPORT, ORG_NAME, START, END, AMT);
        assertThrows(CommandException.class, () -> cmd.execute(modelStub));
        assertTrue(modelStub.contractsAdded.isEmpty());
    }

    // ---------- equals / toString ----------
    @Test
    public void equals() {
        AddContractCommand a = new AddContractCommand(ATHLETE_NAME, SPORT, ORG_NAME, START, END, AMT);
        AddContractCommand same = new AddContractCommand(ATHLETE_NAME, SPORT, ORG_NAME, START, END, AMT);
        AddContractCommand diff = new AddContractCommand(new Name("Kylian Mbappe"), SPORT, ORG_NAME, START, END, AMT);

        assertTrue(a.equals(a));
        assertTrue(a.equals(same));
        assertFalse(a.equals(null));
        assertFalse(a.equals(1));
        assertFalse(a.equals(diff));
    }

    @Test
    public void toStringMethod() {
        AddContractCommand a = new AddContractCommand(ATHLETE_NAME, SPORT, ORG_NAME, START, END, AMT);
        String s = a.toString();
        for (String mustContain : Arrays.asList(
                "athleteName=" + ATHLETE_NAME,
                "sport=" + SPORT,
                "organizationName=" + ORG_NAME,
                "startDate=" + START,
                "endDate=" + END,
                "amount=" + AMT)) {
            assertTrue(s.contains(mustContain), "toString should contain: " + mustContain);
        }
    }

    // ---------- minimal Model stubs ----------
    private class ModelStub implements Model {
        // Organizations
        @Override public boolean hasOrganization(Organization organization) { throw new AssertionError("Should not be called"); }
        @Override public void deleteOrganization(Organization target) { throw new AssertionError("Should not be called"); }
        @Override public void addOrganization(Organization organization) { throw new AssertionError("Should not be called"); }
        @Override public void setOrganization(Organization target, Organization editedOrganization) { throw new AssertionError("Should not be called"); }
        @Override public ObservableList<Organization> getFilteredOrganizationList() { throw new AssertionError("Should not be called"); }
        @Override public void updateFilteredOrganizationList(Predicate<Organization> predicate) { throw new AssertionError("Should not be called"); }

        // Contracts
        @Override public boolean hasContract(Contract contract) { throw new AssertionError("Should not be called"); }
        @Override public void addContract(Contract contract) { throw new AssertionError("Should not be called"); }
        @Override public void deleteContract(Contract target) { throw new AssertionError("Should not be called"); }
        @Override public ObservableList<Contract> getFilteredContractList() { throw new AssertionError("Should not be called"); }
        @Override public void updateFilteredContractList(Predicate<Contract> predicate) { throw new AssertionError("Should not be called"); }

        // Persons
        @Override public boolean hasPerson(Person person) { throw new AssertionError("Should not be called"); }
        @Override public void deletePerson(Person target) { throw new AssertionError("Should not be called"); }
        @Override public void addPerson(Person person) { throw new AssertionError("Should not be called"); }
        @Override public void setPerson(Person target, Person editedPerson) { throw new AssertionError("Should not be called"); }
        @Override public ObservableList<Person> getFilteredPersonList() { throw new AssertionError("Should not be called"); }
        @Override public void updateFilteredPersonList(Predicate<Person> predicate) { throw new AssertionError("Should not be called"); }

        // File/prefs/gui
        @Override public Path getAddressBookFilePath() { throw new AssertionError("Should not be called"); }
        @Override public void setAddressBookFilePath(Path addressBookFilePath) { throw new AssertionError("Should not be called"); }
        @Override public ReadOnlyUserPrefs getUserPrefs() { throw new AssertionError("Should not be called"); }
        @Override public void setUserPrefs(ReadOnlyUserPrefs userPrefs) { throw new AssertionError("Should not be called"); }
        @Override public GuiSettings getGuiSettings() { throw new AssertionError("Should not be called"); }
        @Override public void setGuiSettings(GuiSettings guiSettings) { throw new AssertionError("Should not be called"); }

        // Athletes
        @Override public void addAthlete(Athlete athlete) { throw new AssertionError("Should not be called"); }
        @Override public boolean hasAthlete(Athlete athlete) { throw new AssertionError("Should not be called"); }
        @Override public void deleteAthlete(Athlete target) { throw new AssertionError("Should not be called"); }
        @Override public ObservableList<Athlete> getFilteredAthleteList() { throw new AssertionError("Should not be called"); }
        @Override public void updateFilteredAthleteList(Predicate<Athlete> predicate) { throw new AssertionError("Should not be called"); }

        // AddressBook
        @Override public void setAddressBook(ReadOnlyAddressBook newData) { throw new AssertionError("Should not be called"); }
        @Override public ReadOnlyAddressBook getAddressBook() { throw new AssertionError("Should not be called"); }
    }

    private class ModelStubAcceptingContractAdded extends ModelStub {
        private final AddressBook backing = new AddressBook();

        final List<Contract> contractsAdded = new ArrayList<>();
        boolean duplicateOnHasContract = false;

        @Override
        public boolean hasContract(Contract contract) {
            return duplicateOnHasContract;
        }

        @Override
        public void addContract(Contract contract) {
            requireNonNull(contract);
            contractsAdded.add(contract);
        }

        // Seed model
        @Override
        public void addAthlete(Athlete athlete) {
            requireNonNull(athlete);
            backing.addAthlete(athlete);
        }

        @Override
        public void addOrganization(Organization organization) {
            requireNonNull(organization);
            backing.addOrganization(organization);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return backing;
        }
    }
}
