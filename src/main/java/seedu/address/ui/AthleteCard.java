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

/** An UI component that displays information of a {@code Athlete}. */
public class AthleteCard extends UiPart<Region> {

    private static final String FXML = "AthleteListCard.fxml";

    public final Athlete athlete;

    @FXML private HBox cardPane;
    @FXML private Label name;
    @FXML private Label id;
    @FXML private Label sport;
    @FXML private Label age;
    @FXML private Label phone;
    @FXML private Label email;

    // One row for all badges (contract + orgs)
    @FXML private FlowPane badgeRow;

    /** Creates a {@code AthleteCard} with the given {@code Athlete} and index to display. */
    public AthleteCard(Athlete athlete, int displayedIndex, ObservableList<Contract> allContracts) {
        super(FXML);
        this.athlete = athlete;

        id.setText(displayedIndex + ". ");
        name.setText(athlete.getName().fullName);
        sport.setText(athlete.getSport().value);
        age.setText("Age: " + athlete.getAge().value);
        phone.setText(athlete.getPhone().value);
        email.setText(athlete.getEmail().value);

        // --- Total Contract Value tag ---
        long totalAmount = athlete.getTotalContractAmount(allContracts);
        String formattedAmount = NumberFormat.getNumberInstance(Locale.US).format(totalAmount);

        badgeRow.getChildren().clear();
        badgeRow.getChildren().add(makeTag("$" + formattedAmount, "tag", "tag-contract"));

        // --- Organization tags ---
        ObservableList<Organization> orgs = athlete.getOrganizations(allContracts);
        if (orgs.isEmpty()) {
            badgeRow.getChildren().add(makeTag("No Org", "tag", "tag-muted"));
        } else {
            orgs.stream()
                .map(o -> o.getName().fullOrganizationName)
                .forEach(n -> badgeRow.getChildren().add(makeTag(n, "tag", "tag-org")));
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
