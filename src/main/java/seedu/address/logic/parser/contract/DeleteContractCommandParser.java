package seedu.address.logic.parser.contract;

import seedu.address.logic.commands.contract.DeleteContractCommand;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contract.Date8;
import seedu.address.model.person.Name;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new DeleteContractCommand object.
 */
public class DeleteContractCommandParser implements Parser<DeleteContractCommand> {

    @Override
    public DeleteContractCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args,
                PREFIX_NAME,          // athlete
                PREFIX_ORG,           // organization
                PREFIX_START_DATE,    // start date
                PREFIX_END_DATE       // end date
        );

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ORG, PREFIX_START_DATE, PREFIX_END_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, DeleteContractCommand.MESSAGE_USAGE));
        }

        // Disallow duplicate fields
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ORG, PREFIX_START_DATE, PREFIX_END_DATE);

        Name athleteName = ContractParserUtil.parseAthleteName(argMultimap.getValue(PREFIX_NAME).get());
        Name organizationName = ContractParserUtil.parseOrgName(argMultimap.getValue(PREFIX_ORG).get());
        Date8 startDate = ContractParserUtil.parseDate8(argMultimap.getValue(PREFIX_START_DATE).get());
        Date8 endDate = ContractParserUtil.parseDate8(argMultimap.getValue(PREFIX_END_DATE).get());

        return new DeleteContractCommand(athleteName, organizationName, startDate, endDate);
    }

    /** Returns true if none of the prefixes contains empty Optional values. */
    private static boolean arePrefixesPresent(ArgumentMultimap mm, Prefix... prefixes) {
        return java.util.stream.Stream.of(prefixes).allMatch(p -> !mm.getAllValues(p).isEmpty());
    }
}
