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
import seedu.address.model.athlete.Name;
import seedu.address.model.athlete.Sport;
import seedu.address.model.contract.Amount;
import seedu.address.model.contract.Contract;
import seedu.address.model.contract.Date8;
import seedu.address.model.organization.OrganizationName;

/**
 * Deletes an existing {@link Contract} identified by athlete/org/dates.
 */
public class DeleteContractCommand extends Command {

    public static final String COMMAND_WORD = "delete-c";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a contract by athlete, organization, and dates. "
            + "Parameters: "
            + "n/NAME "
            + "s/SPORT "
            + "o/ORG_NAME "
            + "sd/DDMMYYYY "
            + "ed/DDMMYYYY "
            + "am/AMOUNT\n"
            + "Example: " + COMMAND_WORD + " n/Lebron James s/Basketball o/Nike sd/01012024 ed/01012025 am/50000000";

    public static final String MESSAGE_SUCCESS = "Deleted contract: %1$s";
    public static final String MESSAGE_CONTRACT_NOT_FOUND =
            "Error: No matching contract found for athlete '%s', sport '%s', organization '%s', "
            + "start '%s', end '%s', amount '%s'.";

    private final Name athleteName;
    private final OrganizationName organizationName;
    private final Date8 startDate;
    private final Date8 endDate;
    private final Sport sport;
    private final Amount amount;

    /**
     * Constructs a {@code DeleteContractCommand} with the specified athlete, organization, and date range.
     *
     * @param athleteName      Name of the athlete whose contract is to be deleted.
     * @param organizationName Name of the organization involved in the contract.
     * @param startDate        Start date of the contract.
     * @param endDate          End date of the contract.
     */
    public DeleteContractCommand(Name athleteName, OrganizationName organizationName,
        Date8 startDate, Date8 endDate, Sport sport, Amount amount) {
        requireNonNull(athleteName);
        requireNonNull(organizationName);
        requireNonNull(startDate);
        requireNonNull(endDate);
        requireNonNull(sport);
        requireNonNull(amount);

        this.athleteName = athleteName;
        this.organizationName = organizationName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sport = sport;
        this.amount = amount;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Search the filtered contract list (or use addressBook.getContractList())
        List<Contract> contracts = model.getFilteredContractList();
        Optional<Contract> match = contracts.stream()
                .filter(c ->
                        c.getAthlete().getName().equals(this.athleteName)
                                && c.getOrganization().getName().equals(this.organizationName)
                                && c.getStartDate().equals(this.startDate)
                                && c.getEndDate().equals(this.endDate)
                                && c.getAthlete().getSport().equals(this.sport)
                                && c.getAmount().equals(this.amount))
                .findFirst();

        if (match.isEmpty()) {
            throw new CommandException(String.format(
                    MESSAGE_CONTRACT_NOT_FOUND,
                    athleteName, organizationName, startDate, endDate, sport, amount
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

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteContractCommand)
                && athleteName.equals(((DeleteContractCommand) other).athleteName)
                && sport.equals(((DeleteContractCommand) other).sport)
                && organizationName.equals(((DeleteContractCommand) other).organizationName)
                && startDate.equals(((DeleteContractCommand) other).startDate)
                && endDate.equals(((DeleteContractCommand) other).endDate)
                && amount.equals(((DeleteContractCommand) other).amount);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("athleteName", athleteName)
                .add("sport", sport)
                .add("organizationName", organizationName)
                .add("startDate", startDate)
                .add("endDate", endDate)
                .add("amount", amount)
                .toString();
    }
}
