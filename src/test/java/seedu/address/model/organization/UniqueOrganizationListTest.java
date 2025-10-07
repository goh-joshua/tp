package seedu.address.model.organization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.organization.exceptions.DuplicateOrganizationException;
import seedu.address.model.organization.exceptions.OrganizationNotFoundException;
import seedu.address.testutil.OrganizationBuilder;

/**
 * Tests for {@link UniqueOrganizationList}.
 */
public class UniqueOrganizationListTest {

    private final UniqueOrganizationList uniqueOrganizationList = new UniqueOrganizationList();

    private final Organization nike = new OrganizationBuilder()
            .withName("Nike")
            .withContactName("John Doe")
            .withPhone("98765432")
            .withEmail("john@nike.com")
            .build();

    private final Organization adidas = new OrganizationBuilder()
            .withName("Adidas")
            .withContactName("Jane Smith")
            .withPhone("87654321")
            .withEmail("jane@adidas.com")
            .build();

    @Test
    public void contains_nullOrganization_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrganizationList.contains(null));
    }

    @Test
    public void contains_organizationNotInList_returnsFalse() {
        assertFalse(uniqueOrganizationList.contains(nike));
    }

    @Test
    public void contains_organizationInList_returnsTrue() {
        uniqueOrganizationList.add(nike);
        assertTrue(uniqueOrganizationList.contains(nike));
    }

    @Test
    public void contains_organizationWithSameIdentityFieldsInList_returnsTrue() {
        uniqueOrganizationList.add(nike);
        Organization editedNike = new OrganizationBuilder(nike)
                .withPhone("91234567")
                .withEmail("contact@nike.com")
                .build();
        assertTrue(uniqueOrganizationList.contains(editedNike));
    }

    @Test
    public void add_nullOrganization_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrganizationList.add(null));
    }

    @Test
    public void add_duplicateOrganization_throwsDuplicateOrganizationException() {
        uniqueOrganizationList.add(nike);
        assertThrows(DuplicateOrganizationException.class, () -> uniqueOrganizationList.add(nike));
    }

    @Test
    public void setOrganization_nullTargetOrganization_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrganizationList.setOrganization(null, nike));
    }

    @Test
    public void setOrganization_nullEditedOrganization_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrganizationList.setOrganization(nike, null));
    }

    @Test
    public void setOrganization_targetOrganizationNotInList_throwsOrganizationNotFoundException() {
        assertThrows(OrganizationNotFoundException.class, () -> uniqueOrganizationList.setOrganization(nike, nike));
    }

    @Test
    public void setOrganization_editedOrganizationIsSameOrganization_success() {
        uniqueOrganizationList.add(nike);
        uniqueOrganizationList.setOrganization(nike, nike);
        UniqueOrganizationList expectedList = new UniqueOrganizationList();
        expectedList.add(nike);
        assertEquals(expectedList, uniqueOrganizationList);
    }

    @Test
    public void setOrganization_editedOrganizationHasSameIdentity_success() {
        uniqueOrganizationList.add(nike);
        Organization editedNike = new OrganizationBuilder(nike)
                .withPhone("91234567")
                .withEmail("contact@nike.com")
                .build();
        uniqueOrganizationList.setOrganization(nike, editedNike);
        UniqueOrganizationList expectedList = new UniqueOrganizationList();
        expectedList.add(editedNike);
        assertEquals(expectedList, uniqueOrganizationList);
    }

    @Test
    public void setOrganization_editedOrganizationHasDifferentIdentity_success() {
        uniqueOrganizationList.add(nike);
        uniqueOrganizationList.setOrganization(nike, adidas);
        UniqueOrganizationList expectedList = new UniqueOrganizationList();
        expectedList.add(adidas);
        assertEquals(expectedList, uniqueOrganizationList);
    }

    @Test
    public void setOrganization_editedOrganizationHasNonUniqueIdentity_throwsDuplicateOrganizationException() {
        uniqueOrganizationList.add(nike);
        uniqueOrganizationList.add(adidas);
        assertThrows(DuplicateOrganizationException.class, ()
                -> uniqueOrganizationList.setOrganization(nike, adidas));
    }

    @Test
    public void remove_nullOrganization_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrganizationList.remove(null));
    }

    @Test
    public void remove_organizationDoesNotExist_throwsOrganizationNotFoundException() {
        assertThrows(OrganizationNotFoundException.class, () -> uniqueOrganizationList.remove(nike));
    }

    @Test
    public void remove_existingOrganization_removesOrganization() {
        uniqueOrganizationList.add(nike);
        uniqueOrganizationList.remove(nike);
        UniqueOrganizationList expectedList = new UniqueOrganizationList();
        assertEquals(expectedList, uniqueOrganizationList);
    }

    @Test
    public void setOrganizations_nullUniqueOrganizationList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> uniqueOrganizationList.setOrganizations((UniqueOrganizationList) null));
    }

    @Test
    public void setOrganizations_uniqueOrganizationList_replacesOwnListWithProvidedUniqueOrganizationList() {
        uniqueOrganizationList.add(nike);
        UniqueOrganizationList replacementList = new UniqueOrganizationList();
        replacementList.add(adidas);
        uniqueOrganizationList.setOrganizations(replacementList);
        assertEquals(replacementList, uniqueOrganizationList);
    }

    @Test
    public void setOrganizations_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> uniqueOrganizationList.setOrganizations((List<Organization>) null));
    }

    @Test
    public void setOrganizations_list_replacesOwnListWithProvidedList() {
        uniqueOrganizationList.add(nike);
        List<Organization> list = Collections.singletonList(adidas);
        uniqueOrganizationList.setOrganizations(list);
        UniqueOrganizationList expectedList = new UniqueOrganizationList();
        expectedList.add(adidas);
        assertEquals(expectedList, uniqueOrganizationList);
    }

    @Test
    public void setOrganizations_listWithDuplicateOrganizations_throwsDuplicateOrganizationException() {
        List<Organization> duplicates = Arrays.asList(nike, nike);
        assertThrows(DuplicateOrganizationException.class, ()
                -> uniqueOrganizationList.setOrganizations(duplicates));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueOrganizationList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueOrganizationList.asUnmodifiableObservableList().toString(),
                uniqueOrganizationList.toString());
    }
}
