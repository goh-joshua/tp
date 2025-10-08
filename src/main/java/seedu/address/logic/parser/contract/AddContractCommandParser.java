package seedu.address.logic.parser.contract;

import seedu.address.logic.commands.contract.AddContractCommand;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.athlete.Sport;
import seedu.address.model.contract.Amount;
import seedu.address.model.contract.Date8;
import seedu.address.model.person.Name;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new AddContractCommand object.
 * This parser builds value objects (Name, Sport, Date8, Amount) and the command
 * will resolve the corresponding Person objects during execution.
 */
public class AddContractCommandParser implements Parser<AddContractCommand> {

    @Override
    public AddContractCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args,
                PREFIX_NAME,          // athlete
                PREFIX_SPORT,
                PREFIX_ORG,
                PREFIX_START_DATE,
                PREFIX_END_DATE,
                PREFIX_AMOUNT
        );

        if (!arePrefixesPresent(argMultimap,
                PREFIX_NAME, PREFIX_SPORT, PREFIX_ORG,
                PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_AMOUNT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContractCommand.MESSAGE_USAGE));
        }

        // Disallow duplicate fields
        argMultimap.verifyNoDuplicatePrefixesFor(
                PREFIX_NAME, PREFIX_SPORT, PREFIX_ORG,
                PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_AMOUNT
        );

        // Parse values with the proper utilities
        Name athleteName = ContractParserUtil.parseAthleteName(argMultimap.getValue(PREFIX_NAME).get());
        Sport sport = ContractParserUtil.parseSport(argMultimap.getValue(PREFIX_SPORT).get());
        Name organisationName = ContractParserUtil.parseOrgName(argMultimap.getValue(PREFIX_ORG).get());
        Date8 startDate = ContractParserUtil.parseDate8(argMultimap.getValue(PREFIX_START_DATE).get());
        Date8 endDate = ContractParserUtil.parseDate8(argMultimap.getValue(PREFIX_END_DATE).get());
        Amount amount = ContractParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());

        // Build command (Person resolution happens in AddContractCommand#execute)
        return new AddContractCommand(athleteName, sport, organisationName, startDate, endDate, amount);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap mm, Prefix... prefixes) {
        return java.util.stream.Stream.of(prefixes).allMatch(p -> !mm.getAllValues(p).isEmpty());
    }
}
