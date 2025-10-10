package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.organization.OrganizationContactName;
import seedu.address.model.organization.OrganizationEmail;
import seedu.address.model.organization.OrganizationName;
import seedu.address.model.organization.OrganizationPhone;

public class ParserUtilTest {
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "91234567";
    private static final String VALID_EMAIL = "rachel@example.com";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    // ============================================================
    // Organization parsing methods
    // ============================================================

    @Test
    public void parseOrganizationName_validValue_returnsTrimmedOrganizationName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        OrganizationName expectedName = new OrganizationName(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseOrganizationName(nameWithWhitespace));
    }

    @Test
    public void parseOrganizationContactName_validValue_returnsTrimmedOrganizationContactName() throws Exception {
        String contactWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        OrganizationContactName expectedContact = new OrganizationContactName(VALID_NAME);
        assertEquals(expectedContact, ParserUtil.parseOrganizationContactName(contactWithWhitespace));
    }

    @Test
    public void parseOrganizationPhone_validValue_returnsTrimmedOrganizationPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        OrganizationPhone expectedPhone = new OrganizationPhone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parseOrganizationPhone(phoneWithWhitespace));
    }

    @Test
    public void parseOrganizationEmail_validValue_returnsTrimmedOrganizationEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        OrganizationEmail expectedEmail = new OrganizationEmail(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseOrganizationEmail(emailWithWhitespace));
    }
}
