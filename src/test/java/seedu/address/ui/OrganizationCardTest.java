package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import seedu.address.model.organization.Organization;
import seedu.address.testutil.OrganizationBuilder;

public class OrganizationCardTest {

    @Test
    public void organizationCard_validOrganization_fieldsCorrect() {
        Organization organization = new OrganizationBuilder().build();
        
        assertNotNull(organization);
        assertEquals("Nike", organization.getName().fullOrganizationName);
    }

    @Test
    public void organizationCard_customData_fieldsCorrect() {
        Organization organization = new OrganizationBuilder()
                .withName("Test Sports Corp")
                .withContactName("John Manager")
                .withPhone("87654321")
                .withEmail("contact@testsports.com")
                .build();

        assertNotNull(organization);
        assertEquals("Test Sports Corp", organization.getName().fullOrganizationName);
        assertEquals("John Manager", organization.getContactName().fullName);
        assertEquals("87654321", organization.getPhone().value);
        assertEquals("contact@testsports.com", organization.getEmail().value);
    }
}