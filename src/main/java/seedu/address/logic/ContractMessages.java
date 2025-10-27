package seedu.address.logic;

import seedu.address.model.contract.Contract;

/**
 * Contains helper messages and formatting utilities for displaying {@link Contract} information
 * in command results or logs.
 */
public class ContractMessages {
    public static final String MESSAGE_INVALID_CONTRACT_DISPLAYED_INDEX =
        "Error: The contract index provided is invalid";
    public static final String MESSAGE_CONTRACTS_LISTED_OVERVIEW = "%1$d contracts listed!";

    /**
     * Returns a formatted string containing details of the given {@link Contract}.
     *
     * @param contract the contract to format
     * @return a human-readable string representation of the contract
     */
    public static String format(Contract contract) {
        // Adjust fields to your actual getters/toString of value objects
        return new StringBuilder()
                .append("Athlete: ").append(contract.getAthlete().getName())
                .append("; Sport: ").append(contract.getSport())
                .append("; Organization: ").append(contract.getOrganization().getName())
                .append("; Start: ").append(contract.getStartDate())
                .append("; End: ").append(contract.getEndDate())
                .append("; Amount: ").append(contract.getAmount())
                .toString();
    }
}
