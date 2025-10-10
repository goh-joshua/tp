package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.athlete.Athlete;

/**
 * An UI component that displays information of a {@code Athlete}.
 */
public class AthleteCard extends UiPart<Region> {

    private static final String FXML = "AthleteListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Athlete athlete;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label sport;
    @FXML
    private Label age;
    @FXML
    private Label phone;
    @FXML
    private Label email;

    /**
     * Creates a {@code AthleteCard} with the given {@code Athlete} and index to display.
     */
    public AthleteCard(Athlete athlete, int displayedIndex) {
        super(FXML);
        this.athlete = athlete;
        id.setText(displayedIndex + ". ");
        name.setText(athlete.getName().fullName);
        sport.setText(athlete.getSport().value);
        age.setText("Age: " + athlete.getAge().value);
        phone.setText(athlete.getPhone().value);
        email.setText(athlete.getEmail().value);
    }
}
