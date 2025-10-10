package seedu.address.model.contract;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a contract list.
 */
public interface ReadOnlyContractList {
    /** Returns an unmodifiable view of the contracts list */
    ObservableList<Contract> getContractList();
}
