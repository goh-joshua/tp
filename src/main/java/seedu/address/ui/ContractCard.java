package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.contract.Contract;

/**
 * An UI component that displays information of a {@code Contract}.
 */
public class ContractCard extends UiPart<Region> {

    private static final String FXML = "ContractListCard.fxml";

    public final Contract contract;

    @FXML private HBox cardPane;
    @FXML private Label id;
    @FXML private Label athleteInfo;
    @FXML private Label organizationInfo;
    @FXML private Label duration;
    @FXML private FlowPane amountTag;

    /** Creates a {@code ContractCard} with the given {@code Contract} and index to display. */
    public ContractCard(Contract contract, int displayedIndex) {
        super(FXML);
        this.contract = contract;

        id.setText(displayedIndex + ". ");
        athleteInfo.setText(contract.getAthlete().getName().fullName + " - " + contract.getSport().value);
        organizationInfo.setText("Organization: " + contract.getOrganization().getName().fullOrganizationName);
        duration.setText("Duration: " + formatDate(contract.getStartDate().value)
                + " → " + formatDate(contract.getEndDate().value));

        // --- Amount Tag ---
        amountTag.getChildren().clear();
        String formattedAmount = formatAmount(contract.getAmount().value);
        Label amtChip = makeTag("$" + formattedAmount, "tag", "tag-contract");
        amountTag.getChildren().add(amtChip);
    }

    /** Helper to create a chip-style label. */
    private Label makeTag(String text, String... styleClasses) {
        Label l = new Label(text);
        l.getStyleClass().addAll(styleClasses);
        l.setPadding(new Insets(3, 10, 3, 10));
        return l;
    }

    /** Format a date string (DDMMYYYY → DD/MM/YYYY). */
    private String formatDate(String date) {
        if (date.length() == 8) {
            return date.substring(0, 2) + "/" + date.substring(2, 4) + "/" + date.substring(4, 8);
        }
        return date;
    }

    /** Format an amount with comma separators. */
    private String formatAmount(long amount) {
        return String.format("%,d", amount);
    }
}
