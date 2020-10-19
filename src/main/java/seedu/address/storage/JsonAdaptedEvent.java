package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Event;
import seedu.address.model.task.MeetingLink;
import seedu.address.model.task.Task;

public class JsonAdaptedEvent extends JsonAdaptedTask {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final String linkDesc;
    private final String linkUrl;
    private final String linkTime;
    /**
     * Constructs a {@code JsonAdaptedEvent} with the given Event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("description") String description, @JsonProperty("isDone") Boolean isDone,
                           @JsonProperty("start") LocalDateTime start, @JsonProperty("end") LocalDateTime end,
                            @JsonProperty("linkDesc") String linkDesc, @JsonProperty("linkUrl") String url,
                            @JsonProperty("linkTime") String linkTime) {
        super(description, isDone);
        this.start = start;
        this.end = end;
        this.linkDesc = linkDesc;
        this.linkUrl = url;
        this.linkTime = linkTime;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Task source) {
        super(source);
        start = source.getStart();
        end = source.getEnd();
        linkDesc = source.getLink().getDescription().split(" ", 2)[0];
        linkUrl = source.getLink().getUrl();
        linkTime = ((Event) source).getMeetingLink().saveTimeFormat();
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

        if (linkUrl == null || linkDesc == null || linkTime == null) {
            return new Event(modelIsDone, modelDescription, modelStart, modelEnd);
        } else {
            return new Event(modelIsDone, modelDescription,
                    modelStart, modelEnd, new MeetingLink(linkDesc, linkUrl, linkTime));
        }

    }
}

