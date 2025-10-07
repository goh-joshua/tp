package seedu.address.logic.commands.organization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.organization.Organization;
import seedu.address.testutil.OrganizationBuilder;

/**
 * Basic unit tests for {@code AddOrganizationCommand}.
 * Functional behavior tests are omitted until model/storage integration is complete.
 */
public class AddOrganizationCommandTest {

    @Test
    public void constructor_nullOrganization_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOrganizationCommand(null));
    }

    @Test
    public void equals() {
        Organization nike = new OrganizationBuilder().withName("Nike").build();
        Organization adidas = new OrganizationBuilder().withName("Adidas").build();
        AddOrganizationCommand addNikeCommand = new AddOrganizationCommand(nike);
        AddOrganizationCommand addAdidasCommand = new AddOrganizationCommand(adidas);

        // same object -> returns true
        assertTrue(addNikeCommand.equals(addNikeCommand));

        // same values -> returns true
        AddOrganizationCommand addNikeCommandCopy = new AddOrganizationCommand(nike);
        assertTrue(addNikeCommand.equals(addNikeCommandCopy));

        // different types -> returns false
        assertFalse(addNikeCommand.equals(1));

        // null -> returns false
        assertFalse(addNikeCommand.equals(null));

        // different organization -> returns false
        assertFalse(addNikeCommand.equals(addAdidasCommand));
    }

    @Test
    public void toStringMethod() {
        Organization nike = new OrganizationBuilder().build();
        AddOrganizationCommand addCommand = new AddOrganizationCommand(nike);
        String expected = AddOrganizationCommand.class.getCanonicalName() + "{toAdd=" + nike + "}";
        assertEquals(expected, addCommand.toString());
    }
}
