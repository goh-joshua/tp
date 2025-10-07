package seedu.address.model.organization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Organization}.
 */
public class OrganizationTest {

    private final OrganizationName nikeName = new OrganizationName("Nike");
    private final OrganizationContactName johnContact = new OrganizationContactName("John Doe");
    private final OrganizationPhone phone = new OrganizationPhone("98765432");
    private final OrganizationEmail email = new OrganizationEmail("john@nike.com");

    private final Organization nike = new Organization(nikeName, johnContact, phone, email);

    @Test
    public void constructor_nullFields_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Organization(null, johnContact, phone, email));
        assertThrows(NullPointerException.class, () -> new Organization(nikeName, null, phone, email));
        assertThrows(NullPointerException.class, () -> new Organization(nikeName, johnContact, null, email));
        assertThrows(NullPointerException.class, () -> new Organization(nikeName, johnContact, phone, null));
    }

    @Test
    public void isSameOrganization() {
        // same object -> true
        assertTrue(nike.isSameOrganization(nike));

        // null -> false
        assertFalse(nike.isSameOrganization(null));

        // same name and contact -> true
        Organization sameNike = new Organization(
                new OrganizationName("Nike"),
                new OrganizationContactName("John Doe"),
                new OrganizationPhone("87654321"),
                new OrganizationEmail("other@nike.com"));
        assertTrue(nike.isSameOrganization(sameNike));

        // different name -> false
        Organization differentName = new Organization(
                new OrganizationName("Adidas"),
                johnContact,
                phone,
                email);
        assertFalse(nike.isSameOrganization(differentName));

        // different contact -> false
        Organization differentContact = new Organization(
                nikeName,
                new OrganizationContactName("Jane Doe"),
                phone,
                email);
        assertFalse(nike.isSameOrganization(differentContact));
    }

    @Test
    public void equals() {
        // same values -> true
        Organization nikeCopy = new Organization(nikeName, johnContact, phone, email);
        assertTrue(nike.equals(nikeCopy));

        // same object -> true
        assertTrue(nike.equals(nike));

        // null -> false
        assertFalse(nike.equals(null));

        // different type -> false
        assertFalse(nike.equals(5));

        // different name -> false
        Organization diffName = new Organization(new OrganizationName("Adidas"), johnContact, phone, email);
        assertFalse(nike.equals(diffName));

        // different contact name -> false
        Organization diffContact = new Organization(nikeName, new OrganizationContactName("Jane Doe"), phone, email);
        assertFalse(nike.equals(diffContact));

        // different phone -> false
        Organization diffPhone = new Organization(nikeName, johnContact, new OrganizationPhone("87654321"), email);
        assertFalse(nike.equals(diffPhone));

        // different email -> false
        Organization diffEmail = new Organization(nikeName, johnContact, phone, new OrganizationEmail("new@nike.com"));
        assertFalse(nike.equals(diffEmail));
    }

    @Test
    public void toStringMethod() {
        String expected = Organization.class.getCanonicalName()
                + "{name=" + nike.getName()
                + ", contactName=" + nike.getContactName()
                + ", phone=" + nike.getPhone()
                + ", email=" + nike.getEmail()
                + "}";
        assertEquals(expected, nike.toString());
    }
}
