package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.organization.Organization;
import seedu.address.model.organization.OrganizationList;
import seedu.address.model.organization.ReadOnlyOrganizationList;

/**
 * An Immutable OrganizationList that is serializable to JSON format.
 */
@JsonRootName(value = "organizationlist")
class JsonSerializableOrganizationList {

    public static final String MESSAGE_DUPLICATE_ORGANIZATION =
        "Organizations list contains duplicate organization(s).";

    private final List<JsonAdaptedOrganization> organizations = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableOrganizationList} with the given organizations.
     */
    @JsonCreator
    public JsonSerializableOrganizationList(
            @JsonProperty("organizations") List<JsonAdaptedOrganization> organizations) {
        this.organizations.addAll(organizations);
    }

    /**
     * Converts a given {@code ReadOnlyOrganizationList} into this class for Jackson use.
     * @param source future changes to this will not affect the created {@code JsonSerializableOrganizationList}.
     */
    public JsonSerializableOrganizationList(ReadOnlyOrganizationList source) {
        organizations.addAll(source.getOrganizationList().stream()
                .map(JsonAdaptedOrganization::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this list into the model's {@code OrganizationList} object.
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public OrganizationList toModelType() throws IllegalValueException {
        OrganizationList list = new OrganizationList();
        for (JsonAdaptedOrganization jsonOrganization : organizations) {
            Organization organization = jsonOrganization.toModelType();
            if (list.hasOrganization(organization)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ORGANIZATION);
            }
            list.addOrganization(organization);
        }
        return list;
    }
}
