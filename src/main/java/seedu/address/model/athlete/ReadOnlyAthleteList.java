package seedu.address.model.athlete;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an athlete list.
 */
public interface ReadOnlyAthleteList {
    /** Returns an unmodifiable view of the athletes list */
    ObservableList<Athlete> getAthleteList();
}
