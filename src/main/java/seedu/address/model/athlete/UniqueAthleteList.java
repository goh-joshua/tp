package seedu.address.model.athlete;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.athlete.exceptions.DuplicateAthleteException;
import seedu.address.model.athlete.exceptions.AthleteNotFoundException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of athletes that enforces uniqueness between its elements and does not allow nulls.
 * An athlete is considered unique by comparing using {@code Athlete#isSameAthlete(Athlete)}. As such, adding of
 * athletes uses Athlete#isSameAthlete(Athlete) for equality so as to ensure that the athlete being added is
 * unique in terms of identity in the UniqueAthleteList. However, the removal of a athlete uses Athlete#equals(Object) so
 * as to ensure that the athlete with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Athlete#isSameAthlete(Athlete)
 */
public class UniqueAthleteList implements Iterable<Athlete> {

    private final ObservableList<Athlete> internalList = FXCollections.observableArrayList();
    private final ObservableList<Athlete> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent athlete as the given argument.
     */
    public boolean contains(Athlete toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAthlete);
    }

    /**
     * Adds an athlete to the list.
     * The person must not already exist in the list.
     */
    public void add(Athlete toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAthleteException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent athlete from the list.
     * The athlete must exist in the list.
     */
    public void remove(Athlete toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AthleteNotFoundException();
        }
    }

    public void setAthletes(UniqueAthleteList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code athletes}.
     * {@code athletes} must not contain duplicate athletes.
     */
    public void setAthletes(List<Athlete> athletes) {
        requireAllNonNull(athletes);
        if (!athletesAreUnique(athletes)) {
            throw new DuplicateAthleteException();
        }

        internalList.setAll(athletes);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Athlete> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Athlete> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueAthleteList)) {
            return false;
        }

        UniqueAthleteList otherUniqueAthleteList = (UniqueAthleteList) other;
        return internalList.equals(otherUniqueAthleteList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code athletes} contains only unique athletes.
     */
    private boolean athletesAreUnique(List<Athlete> athletes) {
        for (int i = 0; i < athletes.size() - 1; i++) {
            for (int j = i + 1; j < athletes.size(); j++) {
                if (athletes.get(i).isSameAthlete(athletes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
