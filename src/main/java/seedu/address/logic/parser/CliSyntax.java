package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    // ===== Athlete-related =====
    public static final Prefix PREFIX_SPORT = new Prefix("s/"); // Sport type (Football, Tennis, etc.)
    public static final Prefix PREFIX_AGE = new Prefix("ag/"); // Age for athlete

    // ===== Organization-related =====
    public static final Prefix PREFIX_ORG = new Prefix("o/"); // Organization name
    public static final Prefix PREFIX_CONTACT_NAME = new Prefix("cn/"); // Contact person for org


    // ===== Contract-related =====
    public static final Prefix PREFIX_START_DATE = new Prefix("sd/"); // Contract start date (DDMMYYYY)
    public static final Prefix PREFIX_END_DATE = new Prefix("ed/"); // Contract end date (DDMMYYYY)
    public static final Prefix PREFIX_AMOUNT = new Prefix("am/"); // Contract amount
}
