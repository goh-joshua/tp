package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.UniqueAthleteList;

/**
 * Wraps all data at the playbook.io level
 * Duplicates are not allowed (by .isSameAthlete comparison)
 */
public class AthleteBook implements ReadOnlyAthleteBook {

    private final UniqueAthleteList athletes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        athletes = new UniqueAthleteList();
    }

    public AthleteBook() {}

    /**
     * Creates an AthleteBook using the Athletes in the {@code toBeCopied}
     */
    public AthleteBook(ReadOnlyAthleteBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the athletes list with {@code athletes}.
     * {@code athletes} must not contain duplicate athletes.
     */
    public void setAthletes(List<Athlete> athletes) {
        this.athletes.setAthletes(athletes);
    }

    /**
     * Resets the existing data of this {@code AthleteBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAthleteBook newData) {
        requireNonNull(newData);

        setAthletes(newData.getAthleteList());
    }

    //// athlete-level operations

    /**
     * Returns true if an athlete with the same identity as {@code athlete} exists in playbook.io.
     */
    public boolean hasAthlete(Athlete athlete) {
        requireNonNull(athlete);
        return athletes.contains(athlete);
    }

    /**
     * Adds an athlete to the athlete book.
     * The athlete must not already exist in playbook.io.
     */
    public void addAthlete(Athlete a) {
        athletes.add(a);
    }

    /**
     * Removes {@code key} from this {@code AthleteBook}.
     * {@code key} must exist in playbook.io.
     */
    public void removeAthlete(Athlete key) {
        athletes.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("athletes", athletes)
                .toString();
    }

    @Override
    public ObservableList<Athlete> getAthleteList() {
        return athletes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AthleteBook)) {
            return false;
        }

        AthleteBook otherAthleteBook = (AthleteBook) other;
        return athletes.equals(otherAthleteBook.athletes);
    }

    @Override
    public int hashCode() {
        return athletes.hashCode();
    }
}
