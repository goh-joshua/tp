package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.FindCommand.MESSAGE_USAGE;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindCommand.SearchScope;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@link FindCommand} object.
 */
public class FindCommandParser implements Parser<FindCommand> {

    private static final Pattern FLAG_AND_QUERY_FORMAT =
            Pattern.compile("^-(?<flag>an|as|on|ca|co|cs)(?:\\s+)(?<query>.+)$", Pattern.DOTALL);

    @Override
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmed = args.trim();
        if (trimmed.isEmpty()) {
            throw new ParseException(String.format(seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_USAGE));
        }

        Matcher matcher = FLAG_AND_QUERY_FORMAT.matcher(trimmed);
        if (!matcher.matches()) {
            throw new ParseException(String.format(seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_USAGE));
        }

        String flag = matcher.group("flag").toLowerCase(Locale.ENGLISH);
        String rawQuery = matcher.group("query").trim();

        if (rawQuery.isEmpty()) {
            throw new ParseException(String.format(seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_USAGE));
        }

        String keyword = stripEnclosingQuotes(rawQuery);

        if (keyword.isEmpty()) {
            throw new ParseException("Search keyword cannot be blank.");
        }

        SearchScope scope = mapFlagToScope(flag);
        return new FindCommand(scope, keyword);
    }

    private SearchScope mapFlagToScope(String flag) throws ParseException {
        switch (flag) {
        case "an":
            return SearchScope.ATHLETE_NAME;
        case "as":
            return SearchScope.ATHLETE_SPORT;
        case "on":
            return SearchScope.ORGANIZATION_NAME;
        case "ca":
            return SearchScope.CONTRACT_ATHLETE;
        case "co":
            return SearchScope.CONTRACT_ORGANIZATION;
        case "cs":
            return SearchScope.CONTRACT_SPORT;
        default:
            throw new ParseException(String.format(seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_USAGE));
        }
    }

    private String stripEnclosingQuotes(String value) {
        if ((value.startsWith("\"") && value.endsWith("\""))
                || (value.startsWith("'") && value.endsWith("'"))) {
            return value.substring(1, value.length() - 1).trim();
        }
        return value;
    }
}
