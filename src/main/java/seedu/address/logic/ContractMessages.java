package seedu.address.logic;

import seedu.address.model.contract.Contract;

public class ContractMessages {
    public static final String MESSAGE_INVALID_CONTRACT_DISPLAYED_INDEX = "The contract index provided is invalid";
    public static final String MESSAGE_CONTRACTS_LISTED_OVERVIEW = "%1$d contracts listed!";

    public static String format(Contract contract) {
        // Adjust fields to your actual getters/toString of value objects
        return new StringBuilder()
                .append("Athlete: ").append(contract.getAthlete().getName())
                .append("; Sport: ").append(contract.getSport())
                .append("; Organisation: ").append(contract.getOrganization().getName())
                .append("; Start: ").append(contract.getStartDate())
                .append("; End: ").append(contract.getEndDate())
                .append("; Amount: ").append(contract.getAmount())
                .toString();
    }
}
