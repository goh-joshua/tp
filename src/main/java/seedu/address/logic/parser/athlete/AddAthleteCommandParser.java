package seedu.address.logic.parser.athlete;

import seedu.address.logic.commands.athlete.AddAthleteCommand;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.athlete.*;

import java.util.stream.Stream;

import static seedu.address.logic.AthleteMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;


/**
 * Parses input arguments and creates a new AddAthleteCommand object
 */
public class AddAthleteCommandParser implements Parser<AddAthleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAthleteCommand
     * and returns an AddAthleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAthleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SPORT, PREFIX_AGE, PREFIX_PHONE, PREFIX_EMAIL);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_SPORT, PREFIX_AGE, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAthleteCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_SPORT, PREFIX_AGE, PREFIX_PHONE, PREFIX_EMAIL);
        Name name = AthleteParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Sport sport = AthleteParserUtil.parseSport(argMultimap.getValue(PREFIX_SPORT).get());
        Age age = AthleteParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get());
        Phone phone = AthleteParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = AthleteParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());


        Athlete athlete = new Athlete(name, sport, age, phone, email);

        return new AddAthleteCommand(athlete);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
