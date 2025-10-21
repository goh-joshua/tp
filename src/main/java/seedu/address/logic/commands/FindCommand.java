package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.model.Model;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.contract.Contract;
import seedu.address.model.organization.Organization;

/**
 * Filters and shows model entities based on query keywords using case-insensitive fuzzy matches.
 * The command temporarily filters the in-memory lists without mutating any saved data.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters the requested list by keyword (case-insensitive, allows close matches).\n"
            + "Exactly one flag must be supplied:\n"
            + "  -an KEYWORD    Shows athletes whose name closely matches KEYWORD.\n"
            + "  -as KEYWORD    Shows athletes whose sport closely matches KEYWORD.\n"
            + "  -on KEYWORD    Shows organisations whose name closely matches KEYWORD.\n"
            + "  -ca KEYWORD    Shows contracts whose athlete's name closely matches KEYWORD.\n"
            + "  -co KEYWORD    Shows contracts whose organisation name closely matches KEYWORD.\n"
            + "  -cs KEYWORD    Shows contracts whose sport closely matches KEYWORD.\n"
            + "Example: " + COMMAND_WORD + " -an Lionel";

    private static final String MESSAGE_RESULTS_FORMAT = "Showing %1$d %2$s closely matching \"%3$s\".";

    /**
     * Supported scopes for the find command.
     */
    public enum SearchScope {
        ATHLETE_NAME("athletes", CommandResult.UiTab.ATHLETES) {
            @Override
            int apply(Model model, String keywordLower) {
                model.updateFilteredAthleteList(athleteMatchesName(keywordLower));
                model.updateFilteredOrganizationList(Model.PREDICATE_SHOW_ALL_ORGANIZATIONS);
                model.updateFilteredContractList(Model.PREDICATE_SHOW_ALL_CONTRACTS);
                return model.getFilteredAthleteList().size();
            }
        },
        ATHLETE_SPORT("athletes", CommandResult.UiTab.ATHLETES) {
            @Override
            int apply(Model model, String keywordLower) {
                model.updateFilteredAthleteList(athleteMatchesSport(keywordLower));
                model.updateFilteredOrganizationList(Model.PREDICATE_SHOW_ALL_ORGANIZATIONS);
                model.updateFilteredContractList(Model.PREDICATE_SHOW_ALL_CONTRACTS);
                return model.getFilteredAthleteList().size();
            }
        },
        ORGANIZATION_NAME("organisations", CommandResult.UiTab.ORGANIZATIONS) {
            @Override
            int apply(Model model, String keywordLower) {
                model.updateFilteredOrganizationList(organizationMatchesName(keywordLower));
                model.updateFilteredAthleteList(Model.PREDICATE_SHOW_ALL_ATHLETES);
                model.updateFilteredContractList(Model.PREDICATE_SHOW_ALL_CONTRACTS);
                return model.getFilteredOrganizationList().size();
            }
        },
        CONTRACT_ATHLETE("contracts", CommandResult.UiTab.CONTRACTS) {
            @Override
            int apply(Model model, String keywordLower) {
                model.updateFilteredContractList(contractMatchesAthleteName(keywordLower));
                model.updateFilteredAthleteList(Model.PREDICATE_SHOW_ALL_ATHLETES);
                model.updateFilteredOrganizationList(Model.PREDICATE_SHOW_ALL_ORGANIZATIONS);
                return model.getFilteredContractList().size();
            }
        },
        CONTRACT_ORGANIZATION("contracts", CommandResult.UiTab.CONTRACTS) {
            @Override
            int apply(Model model, String keywordLower) {
                model.updateFilteredContractList(contractMatchesOrganizationName(keywordLower));
                model.updateFilteredAthleteList(Model.PREDICATE_SHOW_ALL_ATHLETES);
                model.updateFilteredOrganizationList(Model.PREDICATE_SHOW_ALL_ORGANIZATIONS);
                return model.getFilteredContractList().size();
            }
        },
        CONTRACT_SPORT("contracts", CommandResult.UiTab.CONTRACTS) {
            @Override
            int apply(Model model, String keywordLower) {
                model.updateFilteredContractList(contractMatchesSport(keywordLower));
                model.updateFilteredAthleteList(Model.PREDICATE_SHOW_ALL_ATHLETES);
                model.updateFilteredOrganizationList(Model.PREDICATE_SHOW_ALL_ORGANIZATIONS);
                return model.getFilteredContractList().size();
            }
        };

        private final String noun;
        private final CommandResult.UiTab tabToShow;

        SearchScope(String noun, CommandResult.UiTab tabToShow) {
            this.noun = noun;
            this.tabToShow = tabToShow;
        }

        abstract int apply(Model model, String keywordLower);

        CommandResult.UiTab getTabToShow() {
            return tabToShow;
        }

        String getNoun() {
            return noun;
        }
    }

    private static final Pattern WORD_PATTERN = Pattern.compile("[\\p{Alnum}]+");

    private final SearchScope scope;
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} that filters according to {@code scope} and {@code keyword}.
     */
    public FindCommand(SearchScope scope, String keyword) {
        requireNonNull(scope);
        requireNonNull(keyword);
        this.scope = scope;
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String keywordLower = keyword.toLowerCase();
        int matches = scope.apply(model, keywordLower);
        String feedback = String.format(MESSAGE_RESULTS_FORMAT, matches, scope.getNoun(), keyword);
        return new CommandResult(feedback, scope.getTabToShow());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherCommand = (FindCommand) other;
        return scope == otherCommand.scope && keyword.equalsIgnoreCase(otherCommand.keyword);
    }

    @Override
    public int hashCode() {
        return (scope.name() + "|" + keyword.toLowerCase()).hashCode();
    }

    private static boolean matchesFuzzy(String haystack, String needleLower) {
        String haystackLower = haystack.toLowerCase();

        if (haystackLower.contains(needleLower)) {
            return true;
        }

        int allowedDistance = allowableDistance(needleLower.length());
        if (allowedDistance == 0) {
            return false;
        }

        if (levenshteinDistance(haystackLower, needleLower) <= allowedDistance) {
            return true;
        }

        Matcher matcher = WORD_PATTERN.matcher(haystackLower);
        while (matcher.find()) {
            String token = matcher.group();
            if (levenshteinDistance(token, needleLower) <= allowedDistance) {
                return true;
            }
        }

        return false;
    }

    private static int allowableDistance(int length) {
        if (length <= 2) {
            return 0;
        }
        if (length <= 4) {
            return 1;
        }
        if (length <= 7) {
            return 2;
        }
        if (length <= 11) {
            return 3;
        }
        return 4;
    }

    private static int levenshteinDistance(String left, String right) {
        if (left.isEmpty()) {
            return right.length();
        }
        if (right.isEmpty()) {
            return left.length();
        }

        int[] previous = new int[right.length() + 1];
        int[] current = new int[right.length() + 1];

        for (int j = 0; j <= right.length(); j++) {
            previous[j] = j;
        }

        for (int i = 1; i <= left.length(); i++) {
            current[0] = i;
            char leftChar = left.charAt(i - 1);
            for (int j = 1; j <= right.length(); j++) {
                int cost = leftChar == right.charAt(j - 1) ? 0 : 1;
                current[j] = Math.min(Math.min(current[j - 1] + 1, previous[j] + 1), previous[j - 1] + cost);
            }
            int[] temp = previous;
            previous = current;
            current = temp;
        }

        return previous[right.length()];
    }

    private static java.util.function.Predicate<Athlete> athleteMatchesName(String keywordLower) {
        return athlete -> matchesFuzzy(athlete.getName().toString(), keywordLower);
    }

    private static java.util.function.Predicate<Athlete> athleteMatchesSport(String keywordLower) {
        return athlete -> matchesFuzzy(athlete.getSport().toString(), keywordLower);
    }

    private static java.util.function.Predicate<Organization> organizationMatchesName(String keywordLower) {
        return organization -> matchesFuzzy(organization.getName().toString(), keywordLower);
    }

    private static java.util.function.Predicate<Contract> contractMatchesAthleteName(String keywordLower) {
        return contract -> matchesFuzzy(contract.getAthlete().getName().toString(), keywordLower);
    }

    private static java.util.function.Predicate<Contract> contractMatchesOrganizationName(String keywordLower) {
        return contract -> matchesFuzzy(contract.getOrganization().getName().toString(), keywordLower);
    }

    private static java.util.function.Predicate<Contract> contractMatchesSport(String keywordLower) {
        return contract -> matchesFuzzy(contract.getSport().toString(), keywordLower);
    }
}
