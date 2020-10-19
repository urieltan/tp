package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Event;
import seedu.address.model.task.Recurrence;
import seedu.address.model.task.Task;

public class JsonAdaptedEvent extends JsonAdaptedTask {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final Recurrence recurrence;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given Event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("description") String description, @JsonProperty("isDone") Boolean isDone,
                           @JsonProperty("start") LocalDateTime start, @JsonProperty("end") LocalDateTime end,
                            @JsonProperty("recurrence") JsonAdaptedRecurrence recurrence) {
        super(description, isDone);
        this.start = start;
        this.end = end;
        if (recurrence != null) {
            this.recurrence = recurrence.toModelType();
        } else {
            this.recurrence = null;
        }
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Task source) {
        super(source);
        start = source.getStart();
        end = source.getEnd();
        recurrence = source.getRecurrence();
    }

    @Override
    public Task toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "description"));
        }
        final String modelDescription = description;
        if (isDone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "isDone"));
        }
        final boolean modelIsDone = isDone;

        if (start == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "start date and time"));
        }

        final LocalDateTime modelStart = start;

        if (end == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "end date and time"));
        }
        final LocalDateTime modelEnd = end;

        final Recurrence modelRecurrence = recurrence;

        if (modelRecurrence == null) {
            return new Event(modelIsDone, modelDescription, modelStart, modelEnd);
        } else {
            return new Event(modelIsDone, modelDescription, modelStart, modelEnd, modelRecurrence);
        }
    }
}

