package seedu.address.model.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Encapsulates a task to be completed over a span of time (i.e period).
 */

public class Event extends Task {

    /**The format of inputted dates and times that the class can accept. */
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**The format of outputted dates and times by the class. */
    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    /**Index of the end of the first provided date and time.*/
    private static final int END_OF_FIRST_DATE_TIME_INDEX = 15;

    /**Index of the start of the second provided date and time.*/
    private static final int START_OF_SECOND_DATE_TIME_INDEX = 19;

    /**The date and time at which the event begins. */
    private LocalDateTime start;

    /**The date and time at which the event ends. */
    private LocalDateTime end;

    /**
     * Constructs an event that has not been completed with a brief
     * description and period of time.
     *
     * @param description a brief description of the event.
     * @param start the starting date and time of event.
     * @param end the ending date and time of event.
     */
    public Event (String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start, INPUT_DATE_TIME_FORMAT);
        this.end = LocalDateTime.parse(end, INPUT_DATE_TIME_FORMAT);
    }

    /**
     * Constructs an event which may or may not be completed
     * with a brief description and period of time.
     *
     * @param isDone indicates if the event has been completed.
     * @param description a brief description of the event.
     * @param start the starting date and time of event.
     * @param end the ending date and time of event.
     */
    public Event(boolean isDone, String description, String start, String end) {
        super(isDone, description);
        this.start = LocalDateTime.parse(start, INPUT_DATE_TIME_FORMAT);
        this.end = LocalDateTime.parse(end, INPUT_DATE_TIME_FORMAT);
    }

    /**
     * Constructs an event which may or may not be completed
     * with a brief description and period of time.
     *
     * @param isDone indicates if the event has been completed.
     * @param description a brief description of the event.
     * @param start the starting date and time of event.
     * @param end the ending date and time of event.
     */
    public Event(boolean isDone, String description, LocalDateTime start, LocalDateTime end) {
        super(isDone, description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the String representation of the period of time which the event occurred over. in the
     * form of -start- to -end-.
     * Dates and times are in the format of outputFormatter.
     *
     * @return the String representation of the period of which the event occurred over.
     */
    public String getPeriod() {
        return this.start.format(OUTPUT_DATE_TIME_FORMAT).toString() + " to "
               + this.end.format(OUTPUT_DATE_TIME_FORMAT).toString();
    }

    /**
     * Reschedules the period of an event to be of a provided period.
     *
     * @param newPeriod the period that the event should be rescheduled to.
     */
    public void reschedule(String newPeriod) {
        this.start = LocalDateTime.parse(newPeriod.substring(0, END_OF_FIRST_DATE_TIME_INDEX), INPUT_DATE_TIME_FORMAT);
        this.end = LocalDateTime.parse(newPeriod.substring(START_OF_SECOND_DATE_TIME_INDEX), INPUT_DATE_TIME_FORMAT);
    }

    /**
     * Returns the string representation of the event, which includes the status icon, description, and period.
     *
     * @return the string representation of the event.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription() + " (at: " + getPeriod() + ")";
    }
    public String getDescriptionDateTime() {
        // hardcoded [✘] as done feature is not done as of now.
        return "[✘] Event: " + this.description + " (at: " + getPeriod() + ")";
    }

    /**
     * Returns a boolean value indicating if the event is equal to another object by
     * determining if isDone, start, end, and description parameters are equal.
     *
     * @param o an object that is compared to the task to determine if both are equal
     * @return true or false if the event is equal or not equal to the object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Event) {
            Event task = (Event) o;
            boolean isEqualEvents = this.description.equals(task.description) && this.start.equals(task.start)
                    && this.end.equals(task.end) && this.isDone == task.isDone;
            return isEqualEvents;
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
            return "E | 1 | " + this.getDescription() + " | " + this.start.format(INPUT_DATE_TIME_FORMAT)
                    + " to " + this.end.format(INPUT_DATE_TIME_FORMAT);
        } else {
            return "E | 0 | " + this.getDescription() + " | " + this.start.format(INPUT_DATE_TIME_FORMAT)
                    + " to " + this.end.format(INPUT_DATE_TIME_FORMAT);
        }
    }

    @Override
    public LocalDateTime getDeadline() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Event: " + this.description;
    }

    @Override
    public String getDateTime() {
        return this.getPeriod();
    }

    @Override
    public LocalDateTime getLocalDateTime() {
        return this.end;
    }
    @Override
    public boolean isTodo() {
        return false;
    }

    @Override
    public boolean isEvent() {
        return true;
    }
    @Override
    public LocalDateTime getStart() {
        return this.start;
    }
    @Override
    public LocalDateTime getEnd() {
        return this.end;
    }
    public String getStartDateTime() {
        return start.format(INPUT_DATE_TIME_FORMAT);
    }

    public String getEndDateTime() {
        return end.format(INPUT_DATE_TIME_FORMAT);
    }
}


