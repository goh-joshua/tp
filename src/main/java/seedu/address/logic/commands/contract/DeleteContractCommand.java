package seedu.address.logic.commands.contract;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.ContractMessages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.Name;
import seedu.address.model.contract.Contract;
import seedu.address.model.contract.Date8;
import seedu.address.model.organization.Organization;
import seedu.address.model.organization.OrganizationName;

/**
 * Deletes an existing {@link Contract} identified by athlete/org/dates.
 */
public class DeleteContractCommand extends Command {

    public static final String COMMAND_WORD = "delete-c";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a contract by athlete, organisation, and dates. "
            + "Parameters: "
            + "n/NAME "
            + "o/ORG "
            + "sd/DDMMYYYY "
            + "ed/DDMMYYYY\n"
            + "Example: " + COMMAND_WORD + " n/Lionel Messi o/Inter Miami sd/01012024 ed/01012025";

    public static final String MESSAGE_SUCCESS = "Deleted contract: %1$s";
    public static final String MESSAGE_CONTRACT_NOT_FOUND =
            "No matching contract found for athlete '%s', organisation '%s', start '%s', end '%s'.";

    private final Name athleteName;
    private final OrganizationName organizationName;
    private final Date8 startDate;
    private final Date8 endDate;

    /**
     * Constructs a {@code DeleteContractCommand} with the specified athlete, organization, and date range.
     *
     * @param athleteName      Name of the athlete whose contract is to be deleted.
     * @param organizationName Name of the organization involved in the contract.
     * @param startDate        Start date of the contract.
     * @param endDate          End date of the contract.
     */
    public DeleteContractCommand(Name athleteName, OrganizationName organizationName, Date8 startDate, Date8 endDate) {
        requireNonNull(athleteName);
        requireNonNull(organizationName);
        requireNonNull(startDate);
        requireNonNull(endDate);

        this.athleteName = athleteName;
        this.organizationName = organizationName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Resolve names to actual objects first (safer matching)
        Athlete athlete = findAthleteByName(model, athleteName).orElse(null);
        Organization organization = findOrganizationByName(model, organizationName).orElse(null);

        // Search the filtered contract list (or use addressBook.getContractList())
        List<Contract> contracts = model.getFilteredContractList();
        Optional<Contract> match = contracts.stream()
                .filter(c ->
                        c.getAthlete().getName().equals(athleteName)
                                && c.getOrganization().getName().equals(organizationName)
                                && c.getStartDate().equals(startDate)
                                && c.getEndDate().equals(endDate))
                .findFirst();

        if (match.isEmpty()) {
            throw new CommandException(String.format(
                    MESSAGE_CONTRACT_NOT_FOUND,
                    athleteName, organizationName, startDate, endDate
            ));
        }

        Contract toDelete = match.get();
        model.deleteContract(toDelete);
        model.updateFilteredAthleteList(unused -> false);
        model.updateFilteredAthleteList(Model.PREDICATE_SHOW_ALL_ATHLETES);

        model.updateFilteredOrganizationList(unused -> false);
        model.updateFilteredOrganizationList(Model.PREDICATE_SHOW_ALL_ORGANIZATIONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, ContractMessages.format(toDelete)));
    }

    private Optional<Athlete> findAthleteByName(Model model, Name name) {
        return model.getAddressBook().getAthleteList().stream()
                .filter(a -> a.getName().equals(name))
                .findFirst();
    }

    private Optional<Organization> findOrganizationByName(Model model, OrganizationName name) {
        return model.getAddressBook().getOrganizationList().stream()
                .filter(o -> o.getName().equals(name))
                .findFirst();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteContractCommand)
                && athleteName.equals(((DeleteContractCommand) other).athleteName)
                && organizationName.equals(((DeleteContractCommand) other).organizationName)
                && startDate.equals(((DeleteContractCommand) other).startDate)
                && endDate.equals(((DeleteContractCommand) other).endDate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("athleteName", athleteName)
                .add("organizationName", organizationName)
                .add("startDate", startDate)
                .add("endDate", endDate)
                .toString();
    }
}
