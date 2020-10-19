package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.task.Recurrence;
import seedu.address.model.task.Todo;

/**
 * A utility class to help with building To-do objects.
 */
public class TodoBuilder {
    /**
     * The format of inputted dates that the class can accept.
     */
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    /**
     * The format of outputted dates by the class.
     */
    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    private static final String DEFAULT_DESC = "homework";
    private static final String DEFAULT_DATETIME = "12-12-2020 2359";

    private String description;
    private String dateTime;
    private Recurrence recurrence;

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
        String dateTimeOutput = todoToCopy.getDateTime();
        // flip the output and input of LocalDateTime
        dateTime = LocalDateTime.parse(dateTimeOutput, OUTPUT_DATE_TIME_FORMAT).format(INPUT_DATE_TIME_FORMAT);
        recurrence = todoToCopy.getRecurrence();
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

    /**
     * Sets the {@code Recurrence} of the {@code To-do} that we are building.
     */
    public TodoBuilder withRecurrence(String recurrenceInput) {
        String[] recurrenceSplit = recurrenceInput.split(" ");
        Integer recurrenceValue = Integer.parseInt(recurrenceSplit[0]);
        String recurrenceTimePeriod = recurrenceSplit[1];
        this.recurrence = new Recurrence(recurrenceValue, recurrenceTimePeriod);
        return this;
    }

    public Todo build() {
        return new Todo(description, dateTime, recurrence);
    }

}
