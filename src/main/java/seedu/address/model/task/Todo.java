package seedu.address.model.task;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.add.AddTodoCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Todo extends Task {
    /**
     * The format of inputted dates that the class can accept.
     */
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * The format of outputted dates by the class.
     */
    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    /**
     * The deadline of the task to be completed by.
     */
    private LocalDateTime deadline;

    /**The collaborative link url. */
    private CollaborativeLink collaborativeLink;

    /**
     * The recurrence (if any).
     */
    private Recurrence recurrence;

    /**
     * Constructs a task that has not been completed
     * with a brief description and deadline for the task to be completed by.
     *
     * @param description a brief description of the deadline.
     * @param deadline    a String in a specific format (inputFormatter) which specifies a date.
     */
    public Todo(String description, String deadline) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, INPUT_DATE_TIME_FORMAT);
        this.collaborativeLink = new CollaborativeLink();
    }

    /**
     * Constructs a task that has not been completed
     * with a brief description and deadline for the task to be completed by.
     *
     * @param description a brief description of the deadline.
     * @param deadline    a String in a specific format (inputFormatter) which specifies a date.
     * @param collaborativeLink the collaborative link of todo.
     */
    public Todo(String description, String deadline, CollaborativeLink collaborativeLink) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, INPUT_DATE_TIME_FORMAT);
        this.collaborativeLink = collaborativeLink;
    }

    /**
     * Constructs a task that has not been completed
     * with a brief description and deadline for the task to be completed by.
     *
     * @param description a brief description of the deadline.
     * @param deadline    a String in a specific format (inputFormatter) which specifies a date.
     * @param collaborativeLink the collaborative link of todo.
     */
    public Todo(String description, LocalDateTime deadline, CollaborativeLink collaborativeLink) {
        super(description);
        this.deadline = deadline;
        this.collaborativeLink = collaborativeLink;
    }

    /**
     * Constructs a task that has not been completed
     * with a brief description and deadline for the task to be completed by.
     *
     * @param description a brief description of the deadline.
     * @param deadline    a String in a specific format (inputFormatter) which specifies a date.
     * @param recurrence the recurrence of todo.
     */
    public Todo(String description, String deadline, Recurrence recurrence) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, INPUT_DATE_TIME_FORMAT);
        this.recurrence = recurrence;
    }

    /**
     * Constructs a task that has not been completed
     * with a brief description and deadline for the task to be completed by.
     *
     * @param description a brief description of the deadline.
     * @param deadline    a LocalDateTime in a specific format (inputFormatter) which specifies a date.
     * @param recurrence the recurrence of todo.
     */
    public Todo(String description, LocalDateTime deadline, Recurrence recurrence) {
        super(description);
        this.deadline = deadline;
        this.recurrence = recurrence;
    }

    /**
     * Constructs a task, which may or may not have been completed,
     * with a brief description and deadline for the task to be completed by.
     *
     * @param isDone      indicates if the deadline has been completed.
     * @param description a brief description of the deadline.
     * @param deadline    a String in a specific format (inputFormatter) which specifies a date.
     */
    public Todo(boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = LocalDateTime.parse(deadline, INPUT_DATE_TIME_FORMAT);
        this.collaborativeLink = new CollaborativeLink();
    }

    /**
     * Constructs a task, which may or may not have been completed,
     * with a brief description and deadline for the task to be completed by.
     *
     * @param isDone      indicates if the deadline has been completed.
     * @param description a brief description of the deadline.
     * @param deadline    a date and time specifies a deadline.
     */
    public Todo(boolean isDone, String description, LocalDateTime deadline) {
        super(isDone, description);
        this.deadline = deadline;
        this.collaborativeLink = new CollaborativeLink();
    }

    /**
     * Changes the deadline of the task to a provided new deadline.
     *
     * @param newDeadline the deadline of the task to be changed to
     */
    public void snooze(String newDeadline) {
        this.deadline = LocalDateTime.parse(newDeadline, INPUT_DATE_TIME_FORMAT);
    }

    /**
     * Returns a String representation of the deadline with the format of outputFormatter.
     *
     * @return a String representation of the deadline with the format of outputFormatter.
     */
    public String deadlineToString() {
        return this.deadline.format(OUTPUT_DATE_TIME_FORMAT).toString();
    }

    @Override
    public AddCommand markAsDone() {
        this.isDone = true;
        if (this.recurrence != null) {
            LocalDateTime newDateTime = this.getLocalDateTime()
                    .plus(this.recurrence.getValue(), this.recurrence.getChronoUnit());
            AddTodoCommand command = new AddTodoCommand(new Todo(description, newDateTime, recurrence));
            return command;
        } else {
            return null;
        }
    }

    /**
     * Returns a String representation of the task.
     * This representation includes the status icon, description, and deadline in the format of outputFormatter.
     *
     * @return a String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription() + " (by: " + deadlineToString() + ")";
    }

    /**
     * Returns a boolean value indicating if the task is equal to
     * another object by determining if descriptions, deadline, and isDone parameters
     * are equal.
     *
     * @param o an object that is compared to the task to determine if both are equal
     * @return true or false if the task is equal or not equal to the object respectively.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Todo) {
            Todo task = (Todo) o;
            boolean isEqualDeadlines = this.description.equals(task.description)
                    && this.deadline.equals(task.deadline) && this.isDone == task.isDone;
            return isEqualDeadlines;
        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of the task in a format to be inputted into a text file for data storage.
     *
     * @return the string representation of the task to be saved in a text file.
     */
    @Override
    public String saveFormat() {
        if (isDone) {
            return "D | 1 | " + this.getDescription() + " | " + this.deadline.format(INPUT_DATE_TIME_FORMAT).toString();
        } else {
            return "D | 0 | " + this.getDescription() + " | " + this.deadline.format(INPUT_DATE_TIME_FORMAT).toString();
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getDateTime() {
        return deadlineToString();
    }

    @Override
    public LocalDateTime getLocalDateTime() {
        return this.deadline;
    }

    @Override
    public boolean isTodo() {
        return true;
    }

    @Override
    public boolean isEvent() {
        return false;
    }

    public String getDescriptionDateTime() {
        return this.description + " (by: " + getDateTime() + ")";
    }

    @Override
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public LocalDateTime getStart() {
        return null;
    }

    @Override
    public LocalDateTime getEnd() {
        return null;
    }
    @Override
    public String getType() {
        return "Todo";
    }

    @Override
    public Link getLink() {
        return this.collaborativeLink;
    }

    @Override
    public Recurrence getRecurrence() {
        return this.recurrence;
    }
}
