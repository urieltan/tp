package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.CollaborativeLink;
import seedu.address.model.task.Recurrence;
import seedu.address.model.task.Task;
import seedu.address.model.task.Todo;

/**
 * Jackson-friendly version of {@link Todo}.
 */
public class JsonAdaptedTodo extends JsonAdaptedTask {

    private final LocalDateTime deadline;
    private final String linkDesc;
    private final String linkUrl;
    private final Recurrence recurrence;

    /**
     * Constructs a {@code JsonAdaptedTodo} with the given Todo details.
     */
    @JsonCreator
    public JsonAdaptedTodo(@JsonProperty("description") String description, @JsonProperty("isDone") Boolean isDone,
                           @JsonProperty("deadline") LocalDateTime deadline, @JsonProperty("linkDesc") String linkDesc,
                           @JsonProperty("linkUrl") String url,
                           @JsonProperty("recurrence") JsonAdaptedRecurrence recurrence) {
        super(description, isDone);
        this.deadline = deadline;
        this.linkDesc = linkDesc;
        this.linkUrl = url;
        if (recurrence != null) {
            this.recurrence = recurrence.toModelType();
        } else {
            this.recurrence = null;
        }
    }

    /**
     * Converts a given {@code Todo} into this class for Jackson use.
     */
    public JsonAdaptedTodo(Task source) {
        super(source);
        deadline = source.getDeadline();
        if (source.getLink().isPresent()) {
            linkDesc = source.getLink().get().getDescription();
            linkUrl = source.getLink().get().getUrl();
        } else {
            linkDesc = "";
            linkUrl = "";
        }
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

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "deadline"));
        }

        final LocalDateTime modelDeadline = deadline;
        final Recurrence modelRecurrence = recurrence;

        if (linkUrl == "" || linkDesc == "") {
            if (modelRecurrence == null) {
                return new Todo(modelIsDone, modelDescription, modelDeadline);
            } else {
                return new Todo(modelIsDone, modelDescription, modelDeadline, modelRecurrence);
            }
        } else {
            if (modelRecurrence == null) {
                return new Todo(modelIsDone, modelDescription, modelDeadline, new CollaborativeLink(linkDesc, linkUrl));
            } else {
                return new Todo(modelIsDone, modelDescription, modelDeadline,
                        modelRecurrence, new CollaborativeLink(linkDesc, linkUrl));
            }
        }
    }
}
