package seedu.address.ui;

import java.text.NumberFormat;
import java.util.Locale;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.contract.Contract;
import seedu.address.model.organization.Organization;

/** An UI component that displays information of a {@code Organization}. */
public class OrganizationCard extends UiPart<Region> {

    private static final String FXML = "OrganizationListCard.fxml";

    public final Organization organization;

    @FXML private HBox cardPane;
    @FXML private Label name;
    @FXML private Label id;
    @FXML private Label phone;
    @FXML private Label email;

    // FlowPane for tags (total contract + athletes)
    @FXML private FlowPane tagRow;

    /** Creates a {@code OrganizationCard} with the given {@code Organization} and index to display. */
    public OrganizationCard(Organization organization, int displayedIndex, ObservableList<Contract> allContracts) {
        super(FXML);
        this.organization = organization;

        id.setText(displayedIndex + ". ");
        name.setText(organization.getName().fullOrganizationName);
        phone.setText(organization.getPhone().value);
        email.setText(organization.getEmail().value);

        // --- Build Tags Row ---
        tagRow.getChildren().clear();

        // Total Contract Tag
        long totalAmount = organization.getTotalContractAmount(allContracts);
        String formattedAmount = NumberFormat.getNumberInstance(Locale.US).format(totalAmount);
        tagRow.getChildren().add(makeTag("$" + formattedAmount, "tag", "tag-contract"));

        // Athlete Tags
        ObservableList<Athlete> athletes = organization.getAthletes(allContracts);
        if (athletes.isEmpty()) {
            tagRow.getChildren().add(makeTag("No Athletes", "tag", "tag-muted"));
        } else {
            athletes.stream()
                    .map(a -> a.getName().fullName)
                    .forEach(name -> tagRow.getChildren().add(makeTag(name, "tag", "tag-athlete")));
        }
    }

    /** Helper to create a chip-style tag label. */
    private Label makeTag(String text, String... styleClasses) {
        Label l = new Label(text);
        l.getStyleClass().addAll(styleClasses);
        l.setPadding(new Insets(3, 10, 3, 10));
        return l;
    }
}
