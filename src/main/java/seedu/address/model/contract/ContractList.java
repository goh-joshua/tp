package seedu.address.model.contract;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Mutable list of contracts with uniqueness guarantees, exposed as ReadOnlyContractList.
 * Mirrors the contract-specific subset of AddressBook behavior.
 */
public class ContractList implements ReadOnlyContractList {

    private final UniqueContractList contracts = new UniqueContractList();

    /**
     * Creates an empty ContractList.
     */
    public ContractList() {}

    /**
     * Creates a ContractList by copying from a read-only source.
     * @param toBeCopied the source list to copy from
     */
    public ContractList(ReadOnlyContractList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    // Overwrite operations
    /**
     * Replaces the contents of the contract list with {@code contracts}.
     * Duplicates are not allowed.
     */
    public void setContracts(List<Contract> contracts) {
        this.contracts.setContracts(contracts);
    }

    /**
     * Resets the existing data of this {@code ContractList} with {@code newData}.
     */
    public void resetData(ReadOnlyContractList newData) {
        requireNonNull(newData);
        setContracts(newData.getContractList());
    }

    // Contract-level operations
    /**
     * Returns true if a contract with the same identity as {@code contract} exists in the list.
     */
    public boolean hasContract(Contract contract) {
        requireNonNull(contract);
        return contracts.contains(contract);
    }

    /**
     * Adds a contract to the list. The contract must not already exist in the list.
     */
    public void addContract(Contract contract) {
        contracts.add(contract);
    }

    /**
     * Removes the equivalent contract from the list. The contract must exist in the list.
     */
    public void removeContract(Contract contract) {
        contracts.remove(contract);
    }

    /**
     * Returns an unmodifiable view of the internal list of contracts.
     */
    public ObservableList<Contract> getContractList() {
        return contracts.asUnmodifiableObservableList();
    }
}
