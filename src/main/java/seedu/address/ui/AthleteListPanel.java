package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.contract.Contract;

/**
 * Panel containing the list of athletes.
 */
public class AthleteListPanel extends UiPart<Region> {
    private static final String FXML = "AthleteListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AthleteListPanel.class);

    @FXML
    private ListView<Athlete> athleteListView;
    private final ObservableList<Contract> allContracts;
    private final ObservableList<Contract> allNonFilteredContracts;

    /**
     * Creates a {@code AthleteListPanel} with the given {@code ObservableList}.
     */
    public AthleteListPanel(ObservableList<Athlete> athleteList,
        ObservableList<Contract> contractList, ObservableList<Contract> nonFilteredContractList) {
        super(FXML);
        this.allContracts = contractList;
        this.allNonFilteredContracts = nonFilteredContractList;
        athleteListView.setItems(athleteList);
        athleteListView.setCellFactory(listView -> new AthleteListViewCell(allContracts));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Athlete} using a {@code AthleteCard}.
     */
    class AthleteListViewCell extends ListCell<Athlete> {
        private final ObservableList<Contract> allContracts;

        AthleteListViewCell(ObservableList<Contract> allContracts) {
            this.allContracts = allContracts;

            // Toggle select/deselect on click
            addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
                if (isEmpty()) {
                    return;
                }

                var lv = getListView();
                int idx = getIndex();
                var sel = lv.getSelectionModel();

                if (sel.isSelected(idx)) {
                    sel.clearSelection();
                    e.consume(); // prevent default re-select
                } else {
                    sel.clearSelection();
                    sel.select(idx);
                    e.consume();
                }
            });
        }

        @Override
        protected void updateItem(Athlete athlete, boolean empty) {
            super.updateItem(athlete, empty);

            if (empty || athlete == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AthleteCard(athlete, getIndex() + 1, allNonFilteredContracts).getRoot());
            }
        }
    }

}
