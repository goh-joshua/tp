package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.athlete.Athlete;
import seedu.address.model.athlete.AthleteList;
import seedu.address.model.athlete.ReadOnlyAthleteList;

/**
 * An Immutable AthleteList that is serializable to JSON format.
 */
@JsonRootName(value = "athletelist")
class JsonSerializableAthleteList {

    public static final String MESSAGE_DUPLICATE_ATHLETE = "Athletes list contains duplicate athlete(s).";

    private final List<JsonAdaptedAthlete> athletes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAthleteList} with the given athletes.
     */
    @JsonCreator
    public JsonSerializableAthleteList(@JsonProperty("athletes") List<JsonAdaptedAthlete> athletes) {
        this.athletes.addAll(athletes);
    }

    /**
     * Converts a given {@code ReadOnlyAthleteList} into this class for Jackson use.
     * @param source future changes to this will not affect the created {@code JsonSerializableAthleteList}.
     */
    public JsonSerializableAthleteList(ReadOnlyAthleteList source) {
        athletes.addAll(source.getAthleteList().stream()
                .map(JsonAdaptedAthlete::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this list into the model's {@code AthleteList} object.
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AthleteList toModelType() throws IllegalValueException {
        AthleteList list = new AthleteList();
        for (JsonAdaptedAthlete jsonAthlete : athletes) {
            Athlete athlete = jsonAthlete.toModelType();
            if (list.hasAthlete(athlete)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ATHLETE);
            }
            list.addAthlete(athlete);
        }
        return list;
    }
}
