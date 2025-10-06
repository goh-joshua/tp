package seedu.address.logic.parser.organization;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.organization.AddOrganizationCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.organization.OrganizationAddress;
import seedu.address.model.organization.OrganizationEmail;
import seedu.address.model.organization.OrganizationName;
import seedu.address.model.organization.Organization;
import seedu.address.model.organization.OrganizationPhone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddOrganizationCommand object
 */
public class AddOrganizationCommandParser implements Parser<AddOrganizationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddOrganizationCommand
     * and returns an AddOrganizationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOrganizationCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrganizationCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
        OrganizationName name = ParserUtil.parseOrganizationName(argMultimap.getValue(PREFIX_NAME).get());
        OrganizationPhone phone = ParserUtil.parseOrganizationPhone(argMultimap.getValue(PREFIX_PHONE).get());
        OrganizationEmail email = ParserUtil.parseOrganizationEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        OrganizationAddress address = ParserUtil.parseOrganizationAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Organization organization = new Organization(name, phone, email, address, tagList);

        return new AddOrganizationCommand(organization);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
