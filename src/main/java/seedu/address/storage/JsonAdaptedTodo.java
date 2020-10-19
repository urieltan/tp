package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.CollaborativeLink;
import seedu.address.model.task.Task;
import seedu.address.model.task.Todo;

/**
 * Jackson-friendly version of {@link Todo}.
 */
public class JsonAdaptedTodo extends JsonAdaptedTask {

    private final LocalDateTime deadline;
    private final String linkDesc;
    private final String linkUrl;

    /**
     * Constructs a {@code JsonAdaptedTodo} with the given Todo details.
     */
    @JsonCreator
    public JsonAdaptedTodo(@JsonProperty("description") String description, @JsonProperty("isDone") Boolean isDone,
                           @JsonProperty("deadline") LocalDateTime deadline, @JsonProperty("linkDesc") String linkDesc,
                           @JsonProperty("linkUrl") String url) {
        super(description, isDone);
        this.deadline = deadline;
        this.linkDesc = linkDesc;
        this.linkUrl = url;
    }

    /**
     * Converts a given {@code Todo} into this class for Jackson use.
     */
    public JsonAdaptedTodo(Task source) {
        super(source);
        deadline = source.getDeadline();
        linkDesc = source.getLink().getDescription();
        linkUrl = source.getLink().getUrl();
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

        if (linkUrl.equals("-") || linkDesc.equals("No collaborative link")) {
            return new Todo(modelIsDone, modelDescription, modelDeadline);
        } else {
            return new Todo(modelIsDone, modelDescription, modelDeadline, new CollaborativeLink(linkDesc, linkUrl));
        }
    }
}
