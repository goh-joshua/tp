package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.contract.Contract;
import seedu.address.model.organization.Organization;

/**
 * Filters and shows model entities based on query keywords.
 * The command temporarily filters the in-memory lists without mutating any saved data.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters the requested list by keyword (case-insensitive).\n"
            + "Exactly one flag must be supplied:\n"
            + "  -an KEYWORD    Shows athletes whose name contains KEYWORD.\n"
            + "  -as KEYWORD    Shows athletes whose sport contains KEYWORD.\n"
            + "  -on KEYWORD    Shows organisations whose name contains KEYWORD.\n"
            + "  -ca KEYWORD    Shows contracts whose athlete's name contains KEYWORD.\n"
            + "  -co KEYWORD    Shows contracts whose organisation name contains KEYWORD.\n"
            + "  -cs KEYWORD    Shows contracts whose sport contains KEYWORD.\n"
            + "Example: " + COMMAND_WORD + " -an Lionel";

    private static final String MESSAGE_RESULTS_FORMAT = "Showing %1$d %2$s matching \"%3$s\".";

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

    private static boolean containsIgnoreCase(String haystack, String needleLower) {
        return haystack.toLowerCase().contains(needleLower);
    }

    private static java.util.function.Predicate<Athlete> athleteMatchesName(String keywordLower) {
        return athlete -> containsIgnoreCase(athlete.getName().toString(), keywordLower);
    }

    private static java.util.function.Predicate<Athlete> athleteMatchesSport(String keywordLower) {
        return athlete -> containsIgnoreCase(athlete.getSport().toString(), keywordLower);
    }

    private static java.util.function.Predicate<Organization> organizationMatchesName(String keywordLower) {
        return organization -> containsIgnoreCase(organization.getName().toString(), keywordLower);
    }

    private static java.util.function.Predicate<Contract> contractMatchesAthleteName(String keywordLower) {
        return contract -> containsIgnoreCase(contract.getAthlete().getName().toString(), keywordLower);
    }

    private static java.util.function.Predicate<Contract> contractMatchesOrganizationName(String keywordLower) {
        return contract -> containsIgnoreCase(contract.getOrganization().getName().toString(), keywordLower);
    }

    private static java.util.function.Predicate<Contract> contractMatchesSport(String keywordLower) {
        return contract -> containsIgnoreCase(contract.getSport().toString(), keywordLower);
    }
}
