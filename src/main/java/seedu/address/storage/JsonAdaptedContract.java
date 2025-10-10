package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.Sport;
import seedu.address.model.contract.Amount;
import seedu.address.model.contract.Contract;
import seedu.address.model.contract.Date8;
import seedu.address.model.organization.Organization;

/** Jackson-friendly version of {@link Contract}. */
class JsonAdaptedContract {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Contract's %s field is missing!";

    private final JsonAdaptedAthlete athlete;
    private final String sport;
    private final JsonAdaptedOrganization organization;
    private final String startDate;
    private final String endDate;
    private final String amount;

    @JsonCreator
    public JsonAdaptedContract(@JsonProperty("athlete") JsonAdaptedAthlete athlete,
                               @JsonProperty("sport") String sport,
                               @JsonProperty("organization") JsonAdaptedOrganization organization,
                               @JsonProperty("startDate") String startDate,
                               @JsonProperty("endDate") String endDate,
                               @JsonProperty("amount") String amount) {
        this.athlete = athlete;
        this.sport = sport;
        this.organization = organization;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    /** Converts a given {@code Contract} into this class for Jackson use. */
    public JsonAdaptedContract(Contract source) {
        this.athlete = new JsonAdaptedAthlete(source.getAthlete());
        this.sport = source.getSport().value;
        this.organization = new JsonAdaptedOrganization(source.getOrganization());
        this.startDate = source.getStartDate().value;
        this.endDate = source.getEndDate().value;
        this.amount = String.valueOf(source.getAmount().value);
    }

    /** Converts this Jackson-friendly adapted object into the model's {@code Contract}. */
    public Contract toModelType() throws IllegalValueException {
        if (athlete == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Athlete"));
        }
        final Athlete modelAthlete = athlete.toModelType();

        if (sport == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Sport.class.getSimpleName()));
        }
        if (!Sport.isValidSport(sport)) {
            throw new IllegalValueException(Sport.MESSAGE_CONSTRAINTS);
        }
        final Sport modelSport = new Sport(sport);

        if (organization == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Organization"));
        }
        final Organization modelOrganization = organization.toModelType();

        if (startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "StartDate"));
        }
        if (!Date8.isValidDate8(startDate)) {
            throw new IllegalValueException(Date8.MESSAGE_CONSTRAINTS);
        }
        final Date8 modelStartDate = new Date8(startDate);

        if (endDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "EndDate"));
        }
        if (!Date8.isValidDate8(endDate)) {
            throw new IllegalValueException(Date8.MESSAGE_CONSTRAINTS);
        }
        final Date8 modelEndDate = new Date8(endDate);

        if (amount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Amount"));
        }
        if (!Amount.isValidAmount(amount)) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        final Amount modelAmount = new Amount(Integer.parseInt(amount));

        return new Contract(modelAthlete, modelSport, modelOrganization, modelStartDate, modelEndDate, modelAmount);
    }
}

