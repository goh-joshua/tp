package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.organization.OrganizationContactName;
import seedu.address.model.organization.OrganizationEmail;
import seedu.address.model.organization.OrganizationName;
import seedu.address.model.organization.OrganizationPhone;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 * Each parse method validates and converts a raw {@code String} input into a strongly-typed model object.
 */
public class ParserUtil {

    /**
     * Message used when an index provided by the user is invalid.
     */
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    // ============================================================
    // Common utility
    // ============================================================

    /**
     * Parses a one-based index string into an {@link Index}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param oneBasedIndex User-provided index string.
     * @return Parsed {@link Index} object.
     * @throws ParseException if the specified index is invalid or non-numeric.
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    // ============================================================
    // Person parsing methods
    // ============================================================

    /**
     * Parses a {@code String name} into a {@link Name}.
     *
     * @param name Name string to parse.
     * @return A valid {@link Name}.
     * @throws ParseException if the input does not satisfy {@link Name#isValidName(String)}.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@link Phone}.
     *
     * @param phone Phone string to parse.
     * @return A valid {@link Phone}.
     * @throws ParseException if the input does not satisfy {@link Phone#isValidPhone(String)}.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@link Address}.
     *
     * @param address Address string to parse.
     * @return A valid {@link Address}.
     * @throws ParseException if the input does not satisfy {@link Address#isValidAddress(String)}.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@link Email}.
     *
     * @param email Email string to parse.
     * @return A valid {@link Email}.
     * @throws ParseException if the input does not satisfy {@link Email#isValidEmail(String)}.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@link Tag}.
     *
     * @param tag Tag string to parse.
     * @return A valid {@link Tag}.
     * @throws ParseException if the input does not satisfy {@link Tag#isValidTagName(String)}.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses a collection of tag strings into a {@link Set} of {@link Tag} objects.
     *
     * @param tags A collection of tag strings.
     * @return A {@link Set} of validated {@link Tag} objects.
     * @throws ParseException if any tag string is invalid.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    // ============================================================
    // Organization parsing methods
    // ============================================================

    /**
     * Parses a {@code String name} into an {@link OrganizationName}.
     *
     * @param name Organization name string to parse.
     * @return A valid {@link OrganizationName}.
     * @throws ParseException if the input does not satisfy {@link OrganizationName#isValidName(String)}.
     */
    public static OrganizationName parseOrganizationName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!OrganizationName.isValidName(trimmedName)) {
            throw new ParseException(OrganizationName.MESSAGE_CONSTRAINTS);
        }
        return new OrganizationName(trimmedName);
    }

    /**
     * Parses a {@code String contactName} into an {@link OrganizationContactName}.
     *
     * @param contactName Contact name string to parse.
     * @return A valid {@link OrganizationContactName}.
     * @throws ParseException if the input does not satisfy {@link OrganizationContactName#isValidName(String)}.
     */
    public static OrganizationContactName parseOrganizationContactName(String contactName) throws ParseException {
        requireNonNull(contactName);
        String trimmedContactName = contactName.trim();
        if (!OrganizationContactName.isValidName(trimmedContactName)) {
            throw new ParseException(OrganizationContactName.MESSAGE_CONSTRAINTS);
        }
        return new OrganizationContactName(trimmedContactName);
    }

    /**
     * Parses a {@code String phone} into an {@link OrganizationPhone}.
     *
     * @param phone Phone string to parse.
     * @return A valid {@link OrganizationPhone}.
     * @throws ParseException if the input does not satisfy {@link OrganizationPhone#isValidPhone(String)}.
     */
    public static OrganizationPhone parseOrganizationPhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!OrganizationPhone.isValidPhone(trimmedPhone)) {
            throw new ParseException(OrganizationPhone.MESSAGE_CONSTRAINTS);
        }
        return new OrganizationPhone(trimmedPhone);
    }

    /**
     * Parses a {@code String email} into an {@link OrganizationEmail}.
     *
     * @param email Email string to parse.
     * @return A valid {@link OrganizationEmail}.
     * @throws ParseException if the input does not satisfy {@link OrganizationEmail#isValidEmail(String)}.
     */
    public static OrganizationEmail parseOrganizationEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!OrganizationEmail.isValidEmail(trimmedEmail)) {
            throw new ParseException(OrganizationEmail.MESSAGE_CONSTRAINTS);
        }
        return new OrganizationEmail(trimmedEmail);
    }
}
