package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand.SearchScope;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.athlete.AthleteList;
import seedu.address.model.contract.ContractList;
import seedu.address.model.organization.OrganizationList;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.TypicalOrganizations;
import seedu.address.testutil.athlete.TypicalAthletes;
import seedu.address.testutil.contract.TypicalContracts;

/**
 * Integration tests for {@link FindCommand}.
 */
public class FindCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        AddressBook baseData = buildPopulatedAddressBook();
        model = new ModelManager(baseData, new UserPrefs(), new AthleteList(), new ContractList(),
                new OrganizationList());
        expectedModel = new ModelManager(baseData, new UserPrefs(), new AthleteList(), new ContractList(),
                new OrganizationList());
    }

    @Test
    public void execute_athleteNameScope_filtersAthletes() {
        String keyword = "Alice";
        FindCommand command = new FindCommand(SearchScope.ATHLETE_NAME, keyword);

        CommandResult expectedResult = new FindCommand(SearchScope.ATHLETE_NAME, keyword).execute(expectedModel);

        assertCommandSuccess(command, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_organizationNameScope_filtersOrganizations() {
        String keyword = "nike";
        FindCommand command = new FindCommand(SearchScope.ORGANIZATION_NAME, keyword);

        CommandResult expectedResult = new FindCommand(SearchScope.ORGANIZATION_NAME, keyword).execute(expectedModel);

        assertCommandSuccess(command, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_contractSportScope_filtersContracts() {
        String keyword = "football";
        FindCommand command = new FindCommand(SearchScope.CONTRACT_SPORT, keyword);

        CommandResult expectedResult = new FindCommand(SearchScope.CONTRACT_SPORT, keyword).execute(expectedModel);

        assertCommandSuccess(command, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_athleteNameScope_fuzzyMatchesMisspelling() {
        String keyword = "Aliec"; // close misspelling of Alice
        FindCommand command = new FindCommand(SearchScope.ATHLETE_NAME, keyword);

        CommandResult expectedResult = new FindCommand(SearchScope.ATHLETE_NAME, keyword).execute(expectedModel);

        assertCommandSuccess(command, model, expectedResult, expectedModel);
        assertTrue(expectedModel.getFilteredAthleteList().stream()
                .anyMatch(athlete -> athlete.getName().toString().equals("Alice Pauline")));
    }

    @Test
    public void execute_athleteNameScope_doesNotMatchUnrelatedKeyword() {
        String keyword = "zzzzzz";
        FindCommand command = new FindCommand(SearchScope.ATHLETE_NAME, keyword);

        CommandResult expectedResult = new FindCommand(SearchScope.ATHLETE_NAME, keyword).execute(expectedModel);

        assertCommandSuccess(command, model, expectedResult, expectedModel);
        assertTrue(expectedModel.getFilteredAthleteList().isEmpty());
    }

    private AddressBook buildPopulatedAddressBook() {
        AddressBookBuilder builder = new AddressBookBuilder();
        TypicalAthletes.getTypicalAthletes().forEach(builder::withAthlete);
        TypicalOrganizations.getTypicalOrganizations().forEach(builder::withOrganization);
        TypicalContracts.getTypicalContracts().forEach(builder::withContract);
        return builder.build();
    }
}
