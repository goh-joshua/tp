package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.Name;
import seedu.address.model.athlete.Sport;
import seedu.address.model.contract.Amount;
import seedu.address.model.contract.Contract;
import seedu.address.model.contract.Date8;
import seedu.address.model.organization.Organization;
import seedu.address.testutil.OrganizationBuilder;
import seedu.address.testutil.athlete.AthleteBuilder;

/**
 * Unit tests for {@link ContractMessages}.
 */
public class ContractMessagesTest {

    private static final Name ATHLETE_NAME = new Name("Lionel Messi");
    private static final Sport SPORT = new Sport("Football");
    private static final Name ORG_NAME = new Name("Inter Miami");
    private static final Date8 START = new Date8("01012024");
    private static final Date8 END = new Date8("01012025");
    private static final Amount AMT = new Amount(5_000_000);

    private Athlete validAthlete() {
        return new AthleteBuilder().withName(ATHLETE_NAME.fullName).build();
    }

    private Organization validOrganization() {
        return new OrganizationBuilder().withName(ORG_NAME.fullName).build();
    }

    @Test
    public void messageConstants_areDefinedCorrectly() {
        assertEquals("Error: The contract index provided is invalid",
                ContractMessages.MESSAGE_INVALID_CONTRACT_DISPLAYED_INDEX);
        assertEquals("%1$d contracts listed!",
                ContractMessages.MESSAGE_CONTRACTS_LISTED_OVERVIEW);
    }

    @Test
    public void format_validContract_success() {
        Athlete athlete = validAthlete();
        Organization organization = validOrganization();

        Contract contract = new Contract(athlete, SPORT, organization, START, END, AMT);
        String formatted = ContractMessages.format(contract);

        // Basic null + content sanity
        assertNotNull(formatted);

        // Match exact structure expected by ContractMessages
        String expected = String.format(
                "Athlete: %s; Sport: %s; Organisation: %s; Start: %s; End: %s; Amount: %s",
                athlete.getName(), SPORT, organization.getName(), START, END, AMT);

        assertEquals(expected, formatted);
    }
}
