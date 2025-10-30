package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** The UI tab to display after executing the command (optional). */
    private final UiTab tabToShow;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, UiTab tabToShow) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.tabToShow = tabToShow;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, null);
    }

    /**
     * Constructs a {@code CommandResult} that requests the UI to show {@code tabToShow}.
     */
    public CommandResult(String feedbackToUser, UiTab tabToShow) {
        this(feedbackToUser, false, false, tabToShow);
    }

    /**
     * Returns the feedback message to be displayed to the user.
     *
     * @return The feedback message.
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Returns whether help information should be shown to the user.
     *
     * @return True if help should be shown, false otherwise.
     */
    public boolean isShowHelp() {
        return showHelp;
    }

    /**
     * Returns whether the application should exit.
     *
     * @return True if the application should exit, false otherwise.
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Returns the UI tab to show, if specified.
     *
     * @return An Optional containing the UI tab to show, or an empty Optional if no specific tab was specified.
     */
    public Optional<UiTab> getTabToShow() {
        return Optional.ofNullable(tabToShow);
    }

    /**
     * Returns true if both CommandResult objects have the same field values.
     *
     * @param other The other object to compare with.
     * @return True if both objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && Objects.equals(tabToShow, otherCommandResult.tabToShow);
    }

    /**
     * Returns the hash code for this CommandResult.
     *
     * @return The hash code based on feedbackToUser, showHelp, exit, and tabToShow.
     */
    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, tabToShow);
    }

    /**
     * Returns a string representation of this CommandResult.
     *
     * @return A formatted string containing all field values.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .add("tabToShow", tabToShow)
                .toString();
    }

    /**
     * Represents a tab within the main window.
     */
    public enum UiTab {
        ATHLETES(0),
        ORGANIZATIONS(1),
        CONTRACTS(2);

        private final int tabIndex;

        UiTab(int tabIndex) {
            this.tabIndex = tabIndex;
        }

        public int getTabIndex() {
            return tabIndex;
        }
    }
}
