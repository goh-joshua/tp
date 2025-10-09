package seedu.address.model.athlete;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Mutable list of athletes with uniqueness guarantees, exposed as ReadOnlyAthleteList.
 * Mirrors the athlete-specific subset of AddressBook behavior.
 */
public class AthleteList implements ReadOnlyAthleteList {

    private final UniqueAthleteList athletes = new UniqueAthleteList();

    /**
     * Creates an empty AthleteList.
     */
    public AthleteList() {}

    /**
     * Creates an AthleteList by copying from a read-only source.
     * @param toBeCopied the source list to copy from
     */
    public AthleteList(ReadOnlyAthleteList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    // Overwrite operations
    /**
     * Replaces the contents of the athlete list with {@code athletes}.
     * Duplicates are not allowed.
     */
    public void setAthletes(List<Athlete> athletes) {
        this.athletes.setAthletes(athletes);
    }

    /**
     * Resets the existing data of this {@code AthleteList} with {@code newData}.
     */
    public void resetData(ReadOnlyAthleteList newData) {
        requireNonNull(newData);
        setAthletes(newData.getAthleteList());
    }

    // Athlete-level operations
    /**
     * Returns true if an athlete with the same identity as {@code athlete} exists in the list.
     */
    public boolean hasAthlete(Athlete athlete) {
        requireNonNull(athlete);
        return athletes.contains(athlete);
    }

    /**
     * Adds an athlete to the list. The athlete must not already exist in the list.
     */
    public void addAthlete(Athlete athlete) {
        athletes.add(athlete);
    }

    /**
     * Removes the equivalent athlete from the list. The athlete must exist in the list.
     */
    public void removeAthlete(Athlete athlete) {
        athletes.remove(athlete);
    }

    /**
     * Returns an unmodifiable view of the internal list of athletes.
     */
    public ObservableList<Athlete> getAthleteList() {
        return athletes.asUnmodifiableObservableList();
    }
}
