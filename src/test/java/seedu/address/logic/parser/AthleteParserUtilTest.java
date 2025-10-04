package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.athlete.Age;
import seedu.address.model.athlete.Email;
import seedu.address.model.athlete.Name;
import seedu.address.model.athlete.Phone;
import seedu.address.model.athlete.Sport;

public class AthleteParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_SPORT = "Badminton1";
    private static final String INVALID_AGE = "three";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_SPORT = "Badminton";
    private static final String VALID_AGE = "18";
    private static final String VALID_PHONE = "90123456";
    private static final String VALID_EMAIL = "rachel@example.com";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> AthleteParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> AthleteParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, AthleteParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, AthleteParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseSport_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> AthleteParserUtil.parseSport((String) null));
    }

    @Test
    public void parseSport_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> AthleteParserUtil.parseSport(INVALID_SPORT));
    }

    @Test
    public void parseSport_validValueWithoutWhitespace_returnsName() throws Exception {
        Sport expectedSport = new Sport(VALID_SPORT);
        assertEquals(expectedSport, AthleteParserUtil.parseSport(VALID_SPORT));
    }

    @Test
    public void parseSport_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String sportWithWhitespace = WHITESPACE + VALID_SPORT + WHITESPACE;
        Sport expectedSport = new Sport(VALID_SPORT);
        assertEquals(expectedSport, AthleteParserUtil.parseSport(sportWithWhitespace));
    }

    @Test
    public void parseAge_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> AthleteParserUtil.parseAge((String) null));
    }

    @Test
    public void parseAge_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> AthleteParserUtil.parseAge(INVALID_AGE));
    }

    @Test
    public void parseAge_validValueWithoutWhitespace_returnsName() throws Exception {
        Age expectedAge = new Age(VALID_AGE);
        assertEquals(expectedAge, AthleteParserUtil.parseAge(VALID_AGE));
    }

    @Test
    public void parseAge_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String ageWithWhitespace = WHITESPACE + VALID_AGE + WHITESPACE;
        Age expectedAge = new Age(VALID_AGE);
        assertEquals(expectedAge, AthleteParserUtil.parseAge(ageWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> AthleteParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> AthleteParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, AthleteParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, AthleteParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> AthleteParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> AthleteParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, AthleteParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, AthleteParserUtil.parseEmail(emailWithWhitespace));
    }
}
