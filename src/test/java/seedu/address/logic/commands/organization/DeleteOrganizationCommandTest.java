package seedu.address.logic.commands.organization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

/**
 * Basic unit tests for {@code DeleteOrganizationCommand}.
 * Functional behavior tests are omitted until model/storage integration is complete.
 */
public class DeleteOrganizationCommandTest {

    @Test
    public void equals() {
        DeleteOrganizationCommand deleteFirst = new DeleteOrganizationCommand(Index.fromOneBased(1));
        DeleteOrganizationCommand deleteSecond = new DeleteOrganizationCommand(Index.fromOneBased(2));

        // same object -> true
        assertTrue(deleteFirst.equals(deleteFirst));

        // same values -> true
        assertTrue(deleteFirst.equals(new DeleteOrganizationCommand(Index.fromOneBased(1))));

        // different index -> false
        assertFalse(deleteFirst.equals(deleteSecond));

        // null -> false
        assertFalse(deleteFirst.equals(null));

        // different type -> false
        assertFalse(deleteFirst.equals(1));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteOrganizationCommand command = new DeleteOrganizationCommand(targetIndex);
        String expected = DeleteOrganizationCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, command.toString());
    }
}
