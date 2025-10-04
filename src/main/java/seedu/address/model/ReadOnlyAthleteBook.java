package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.athlete.Athlete;

/**
 * Unmodifiable view of playbook.io
 */
public interface ReadOnlyAthleteBook {

    /**
     * Returns an unmodifiable view of the athletes list.
     * This list will not contain any duplicate athletes.
     */
    ObservableList<Athlete> getAthleteList();

}
