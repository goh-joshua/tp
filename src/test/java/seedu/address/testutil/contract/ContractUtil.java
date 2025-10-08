package seedu.address.testutil.contract;

import seedu.address.logic.commands.contract.AddContractCommand;
import seedu.address.model.contract.Contract;

import static seedu.address.logic.parser.CliSyntax.*;

/**
 * A utility class for Contract.
 */
public class ContractUtil {

    /**
     * Returns an add command string for adding the {@code contract}.
     */
    public static String getAddContractCommand(Contract contract) {
        return AddContractCommand.COMMAND_WORD + " " + getContractDetails(contract);
    }

    /**
     * Returns the part of command string for the given {@code contract}'s details.
     */
    public static String getContractDetails(Contract contract) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME).append(contract.getAthlete().getName().fullName).append(" ");
        sb.append(PREFIX_SPORT).append(contract.getSport().value).append(" ");
        sb.append(PREFIX_ORG).append(contract.getOrganization().getName()).append(" ");
        sb.append(PREFIX_START_DATE).append(contract.getStartDate().value).append(" ");
        sb.append(PREFIX_END_DATE).append(contract.getEndDate().value).append(" ");
        sb.append(PREFIX_AMOUNT).append(contract.getAmount().value).append(" ");
        return sb.toString();
    }
}
