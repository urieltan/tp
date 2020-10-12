package seedu.address.testutil;

import seedu.address.model.task.Todo;

/**
 * A utility class to help with building To-do objects.
 */
public class TodoBuilder {

    public static final String DEFAULT_DESC = "homework";
    public static final String DEFAULT_DATETIME = "12-12-2020 2359";

    private String description;
    private String dateTime;

    /**
     * Creates a {@code TodoBuilder} with the default details.
     */
    public TodoBuilder() {
        description = DEFAULT_DESC;
        dateTime = DEFAULT_DATETIME;
    }

    /**
     * Initializes the TodoBuilder with the data of {@code TodoToCopy}.
     */
    public TodoBuilder(Todo todoToCopy) {
        description = todoToCopy.getDescription();
        dateTime = todoToCopy.getDateTime();
    }

    /**
     * Sets the {@code Description} of the {@code To-do} that we are building.
     */
    public TodoBuilder withDescription(String desc) {
        this.description = desc;
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code To-do} that we are building.
     */
    public TodoBuilder withDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public Todo build() {
        return new Todo(description, dateTime);
    }

}
