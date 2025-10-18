package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult.UiTab;
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

        expectedModel.updateFilteredAthleteList(athlete ->
                athlete.getName().toString().toLowerCase().contains(keyword.toLowerCase()));
        expectedModel.updateFilteredOrganizationList(Model.PREDICATE_SHOW_ALL_ORGANIZATIONS);
        expectedModel.updateFilteredContractList(Model.PREDICATE_SHOW_ALL_CONTRACTS);

        CommandResult expectedResult = new CommandResult(
                String.format("Showing %1$d %2$s matching \"%3$s\".",
                        expectedModel.getFilteredAthleteList().size(),
                        SearchScope.ATHLETE_NAME.getNoun(),
                        keyword),
                UiTab.ATHLETES);

        assertCommandSuccess(command, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_organizationNameScope_filtersOrganizations() {
        String keyword = "nike";
        FindCommand command = new FindCommand(SearchScope.ORGANIZATION_NAME, keyword);

        expectedModel.updateFilteredOrganizationList(organization ->
                organization.getName().toString().toLowerCase().contains(keyword.toLowerCase()));
        expectedModel.updateFilteredAthleteList(Model.PREDICATE_SHOW_ALL_ATHLETES);
        expectedModel.updateFilteredContractList(Model.PREDICATE_SHOW_ALL_CONTRACTS);

        CommandResult expectedResult = new CommandResult(
                String.format("Showing %1$d %2$s matching \"%3$s\".",
                        expectedModel.getFilteredOrganizationList().size(),
                        SearchScope.ORGANIZATION_NAME.getNoun(),
                        keyword),
                UiTab.ORGANIZATIONS);

        assertCommandSuccess(command, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_contractSportScope_filtersContracts() {
        String keyword = "football";
        FindCommand command = new FindCommand(SearchScope.CONTRACT_SPORT, keyword);

        expectedModel.updateFilteredContractList(contract ->
                contract.getSport().toString().toLowerCase().contains(keyword.toLowerCase()));
        expectedModel.updateFilteredAthleteList(Model.PREDICATE_SHOW_ALL_ATHLETES);
        expectedModel.updateFilteredOrganizationList(Model.PREDICATE_SHOW_ALL_ORGANIZATIONS);

        CommandResult expectedResult = new CommandResult(
                String.format("Showing %1$d %2$s matching \"%3$s\".",
                        expectedModel.getFilteredContractList().size(),
                        SearchScope.CONTRACT_SPORT.getNoun(),
                        keyword),
                UiTab.CONTRACTS);

        assertCommandSuccess(command, model, expectedResult, expectedModel);
    }

    private AddressBook buildPopulatedAddressBook() {
        AddressBookBuilder builder = new AddressBookBuilder();
        TypicalAthletes.getTypicalAthletes().forEach(builder::withAthlete);
        TypicalOrganizations.getTypicalOrganizations().forEach(builder::withOrganization);
        TypicalContracts.getTypicalContracts().forEach(builder::withContract);
        return builder.build();
    }
}
