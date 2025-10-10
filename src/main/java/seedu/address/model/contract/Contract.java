package seedu.address.model.contract;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.Sport;
import seedu.address.model.organization.Organization;
import seedu.address.model.organization.OrganizationContactName;


/**
 * Represents a Contract between an Athlete and an Organization.
 * Identity is defined by (athlete, sport, organization, contact, startDate, endDate, amount).
 * Here athlete/organization/contact are full Person objects.
 */
public class Contract {

    // Identity/data fields
    private final Athlete athlete; // full Person
    private final Sport sport; // value object
    private final Organization organization; // full Person
    private final Date8 startDate; // DDMMYYYY validator
    private final Date8 endDate; // DDMMYYYY validator
    private final Amount amount; // positive integer

    /**
     * All fields must be present and non-null.
     */
    public Contract(Athlete athlete,
                    Sport sport,
                    Organization organization,
                    Date8 startDate,
                    Date8 endDate,
                    Amount amount) {
        requireAllNonNull(athlete, sport, organization, startDate, endDate, amount);
        this.athlete = athlete;
        this.sport = sport;
        this.organization = organization;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public Sport getSport() {
        return sport;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Date8 getStartDate() {
        return startDate;
    }

    public Date8 getEndDate() {
        return endDate;
    }

    public Amount getAmount() {
        return amount;
    }

    /**
     * “Same contract” according to your duplicate rule.
     * For Person identity, we use Person#isSamePerson (like AB4’s UniquePersonList).
     */
    public boolean isSameContract(Contract other) {
        if (other == this) {
            return true;
        }
        return other != null
                && this.athlete.isSameAthlete(other.athlete)
                && this.sport.equals(other.sport)
                && this.organization.isSameOrganization(other.organization)
                && this.startDate.equals(other.startDate)
                && this.endDate.equals(other.endDate)
                && this.amount.equals(other.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Contract)) {
            return false;
        }
        Contract c = (Contract) o;
        // Strong equality (compare all fields deeply)
        return athlete.equals(c.athlete)
                && sport.equals(c.sport)
                && organization.equals(c.organization)
                && startDate.equals(c.startDate)
                && endDate.equals(c.endDate)
                && amount.equals(c.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(athlete, sport, organization, startDate, endDate, amount);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("athlete", athlete)
                .add("sport", sport)
                .add("organization", organization)
                .add("startDate", startDate)
                .add("endDate", endDate)
                .add("amount", amount)
                .toString();
    }
}
