package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.task.Recurrence;

public class JsonAdaptedRecurrence {
    private final Integer value;
    private final String unit;

    /**
     * Constructs a {code JsonAdaptedRecurrence} with the given {@code recurrence details}.
     */
    @JsonCreator
    public JsonAdaptedRecurrence(@JsonProperty("value") Integer value, @JsonProperty("chronoUnit") String unit) {
        this.value = value;
        this.unit = unit;
    }

    /**
     * Converts a given {@code Recurrence} into this class for Jackson use.
     */
    public JsonAdaptedRecurrence(Recurrence source) {
        value = source.getValue();
        unit = source.getChronoUnit().toString();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Recurrence} object.
     *
     */
    public Recurrence toModelType() {
        final Integer modelValue = value;
        final String modelUnit;

        if (unit.equals("DAYS")) {
            modelUnit = "day";
        } else if (unit.equals("WEEKS")) {
            modelUnit = "week";
        } else if (unit.equals("MONTHS")) {
            modelUnit = "month";
        } else if (unit.equals("YEARS")) {
            modelUnit = "year";
        } else {
            modelUnit = "";
        }
        return new Recurrence(modelValue, modelUnit);
    }
}
