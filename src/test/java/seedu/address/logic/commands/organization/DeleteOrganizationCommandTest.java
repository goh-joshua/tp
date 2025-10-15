package seedu.address.logic.commands.organization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.organization.Organization;
import seedu.address.model.organization.OrganizationName;
import seedu.address.testutil.OrganizationBuilder;

/**
 * Integration tests for {@code DeleteOrganizationCommand}.
 */
public class DeleteOrganizationCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(); // fresh model for each test
    }

    @Test
    public void execute_existingOrganization_success() throws Exception {
        // Arrange
        Organization organization = new OrganizationBuilder()
                .withName("Nike")
                .withPhone("98765432")
                .withEmail("contact@nike.com")
                .build();
        model.addOrganization(organization);

        DeleteOrganizationCommand command = new DeleteOrganizationCommand(new OrganizationName("Nike"));

        // Act
        var result = command.execute(model);

        // Assert
        String expectedMessage = String.format(DeleteOrganizationCommand.MESSAGE_DELETE_ORGANIZATION_SUCCESS,
                Messages.format(organization));
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(0, model.getFilteredOrganizationList().size());
    }

    @Test
    public void execute_nonexistentOrganization_throwsCommandException() {
        DeleteOrganizationCommand command = new DeleteOrganizationCommand(new OrganizationName("UnknownBrand"));

        assertThrows(CommandException.class,
                String.format(DeleteOrganizationCommand.MESSAGE_ORGANIZATION_NOT_FOUND, "UnknownBrand"), ()
                        -> command.execute(model));
    }

    @Test
    public void execute_caseInsensitiveMatch_success() throws Exception {
        Organization organization = new OrganizationBuilder()
                .withName("Adidas")
                .withPhone("91234567")
                .withEmail("team@adidas.com")
                .build();
        model.addOrganization(organization);

        // delete using lowercase name
        DeleteOrganizationCommand command = new DeleteOrganizationCommand(new OrganizationName("adidas"));
        var result = command.execute(model);

        String expectedMessage = String.format(DeleteOrganizationCommand.MESSAGE_DELETE_ORGANIZATION_SUCCESS,
                Messages.format(organization));
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(0, model.getFilteredOrganizationList().size());
    }

    @Test
    public void execute_deleteOnlyTarget_success() throws Exception {
        Organization nike = new OrganizationBuilder()
                .withName("Nike")
                .withPhone("98765432")
                .withEmail("contact@nike.com")
                .build();

        Organization adidas = new OrganizationBuilder()
                .withName("Adidas")
                .withPhone("91234567")
                .withEmail("team@adidas.com")
                .build();

        model.addOrganization(nike);
        model.addOrganization(adidas);

        DeleteOrganizationCommand command = new DeleteOrganizationCommand(new OrganizationName("Nike"));
        var result = command.execute(model);

        String expectedMessage = String.format(DeleteOrganizationCommand.MESSAGE_DELETE_ORGANIZATION_SUCCESS,
                Messages.format(nike));
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(1, model.getFilteredOrganizationList().size());
        assertEquals(adidas, model.getFilteredOrganizationList().get(0));
    }
}
