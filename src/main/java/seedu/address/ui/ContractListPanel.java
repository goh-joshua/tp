package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.contract.Contract;

/**
 * Panel containing the list of contracts.
 */
public class ContractListPanel extends UiPart<Region> {
    private static final String FXML = "ContractListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ContractListPanel.class);

    @FXML
    private ListView<Contract> contractListView;

    /**
     * Creates a {@code ContractListPanel} with the given {@code ObservableList}.
     */
    public ContractListPanel(ObservableList<Contract> contractList) {
        super(FXML);
        contractListView.setItems(contractList);
        contractListView.setCellFactory(listView -> new ContractListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Contract} using a {@code ContractCard}.
     */
    class ContractListViewCell extends ListCell<Contract> {

        ContractListViewCell() {
            // Toggle selection on click
            addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
                if (isEmpty()) {
                    return;
                }

                var listView = getListView();
                int index = getIndex();

                if (listView.getSelectionModel().isSelected(index)) {
                    listView.getSelectionModel().clearSelection();
                    e.consume();
                } else {
                    listView.getSelectionModel().clearSelection();
                    listView.getSelectionModel().select(index);
                    e.consume();
                }
            });
        }

        @Override
        protected void updateItem(Contract contract, boolean empty) {
            super.updateItem(contract, empty);

            if (empty || contract == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ContractCard(contract, getIndex() + 1).getRoot());
            }
        }
    }
}
