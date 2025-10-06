package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.organization.OrganizationAddress;
import seedu.address.model.organization.OrganizationEmail;
import seedu.address.model.organization.OrganizationName;
import seedu.address.model.organization.OrganizationPhone;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
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

    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

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
     * Parses a {@code String name} into an {@code OrganizationName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
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
     * Parses a {@code String phone} into an {@code OrganizationPhone}.
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
     * Parses a {@code String address} into an {@code OrganizationAddress}.
     */
    public static OrganizationAddress parseOrganizationAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!OrganizationAddress.isValidAddress(trimmedAddress)) {
            throw new ParseException(OrganizationAddress.MESSAGE_CONSTRAINTS);
        }
        return new OrganizationAddress(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code OrganizationEmail}.
     */
    public static OrganizationEmail parseOrganizationEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!OrganizationEmail.isValidEmail(trimmedEmail)) {
            throw new ParseException(OrganizationEmail.MESSAGE_CONSTRAINTS);
        }
        return new OrganizationEmail(trimmedEmail);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseOrganizationTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName)); // reuse existing tag logic
        }
        return tagSet;
    }
}