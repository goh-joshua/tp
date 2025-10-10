package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.contract.Contract;

/**
 * An UI component that displays information of a {@code Contract}.
 */
public class ContractCard extends UiPart<Region> {

    private static final String FXML = "ContractListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Contract contract;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label athleteInfo;
    @FXML
    private Label organizationInfo;
    @FXML
    private Label duration;
    @FXML
    private Label amount;

    /**
     * Creates a {@code ContractCard} with the given {@code Contract} and index to display.
     */
    public ContractCard(Contract contract, int displayedIndex) {
        super(FXML);
        this.contract = contract;
        id.setText(displayedIndex + ". ");
        athleteInfo.setText(contract.getAthlete().getName().fullName + " - " + contract.getSport().value);
        organizationInfo.setText("Organization: " + contract.getOrganization().getName().fullOrganizationName
                + " (Contact: " + contract.getContact().fullName + ")");
        duration.setText("Duration: " + formatDate(contract.getStartDate().value)
                + " → " + formatDate(contract.getEndDate().value));
        amount.setText("Amount: $" + formatAmount(contract.getAmount().value));
    }

    /**
     * Formats a date string from DDMMYYYY to DD/MM/YYYY.
     */
    private String formatDate(String date) {
        if (date.length() == 8) {
            return date.substring(0, 2) + "/" + date.substring(2, 4) + "/" + date.substring(4, 8);
        }
        return date;
    }

    /**
     * Formats an amount with commas for thousands separator.
     */
    private String formatAmount(int amount) {
        return String.format("%,d", amount);
    }
}
