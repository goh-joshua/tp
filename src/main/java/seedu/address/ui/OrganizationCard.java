package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.organization.Organization;

/**
 * An UI component that displays information of a {@code Organization}.
 */
public class OrganizationCard extends UiPart<Region> {

    private static final String FXML = "OrganizationListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Organization organization;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label contactName;
    @FXML
    private Label phone;
    @FXML
    private Label email;

    /**
     * Creates a {@code OrganizationCard} with the given {@code Organization} and index to display.
     */
    public OrganizationCard(Organization organization, int displayedIndex) {
        super(FXML);
        this.organization = organization;
        id.setText(displayedIndex + ". ");
        name.setText(organization.getName().fullOrganizationName);
        contactName.setText("Contact: " + organization.getContactName().fullName);
        phone.setText(organization.getPhone().value);
        email.setText(organization.getEmail().value);
    }
}
