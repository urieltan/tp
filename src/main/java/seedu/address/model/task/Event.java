package seedu.address.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.add.AddEventCommand;
import seedu.address.model.tag.Tag;

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

    /**The meeting link url. */
    private MeetingLink meetingLink;

    /**
     * The recurrence (if any).
     */
    private Recurrence recurrence;

    /**
     * Constructs an event that has not been completed with a brief
     * description and period of time.
     *
     * @param description a brief description of the event.
     * @param start       the starting date and time of event.
     * @param end         the ending date and time of event.
     * @param tags        a set of tags attached to the event.
     */
    public Event (String description, String start, String end, Set<Tag> tags) {
        super(description, tags);
        this.start = LocalDateTime.parse(start, INPUT_DATE_TIME_FORMAT);
        this.end = LocalDateTime.parse(end, INPUT_DATE_TIME_FORMAT);
    }

    /**
     * Constructs an event that has not been completed with a brief
     * description and period of time.
     *
     * @param description a brief description of the event.
     * @param start       the starting date and time of event.
     * @param end         the ending date and time of event.
     * @param meetingLink the meeting link of event.
     * @param tags        a set of tags attached to the event.
     */
    public Event (String description, String start, String end, MeetingLink meetingLink, Set<Tag> tags) {
        super(description, tags);
        this.start = LocalDateTime.parse(start, INPUT_DATE_TIME_FORMAT);
        this.end = LocalDateTime.parse(end, INPUT_DATE_TIME_FORMAT);
        this.meetingLink = meetingLink;
    }

    /**
     * Constructs an event that may or may not be completed with a brief
     * description and period of time and a meeting link.
     * @param description a brief description of the event.
     * @param start       the starting date and time of event.
     * @param end         the ending date and time of event.
     * @param meetingLink the meeting link of event.
     * @param tags        a set of tags attached to the event.
     */
    public Event (boolean isDone, String description, LocalDateTime start,
                  LocalDateTime end, MeetingLink meetingLink, Set<Tag> tags) {
        super(isDone, description, tags);
        this.start = start;
        this.end = end;
        this.meetingLink = meetingLink;
    }

    /**
     * Constructs an event which may or may not be completed
     * with a brief description and period of time.
     *
     * @param isDone      indicates if the event has been completed.
     * @param description a brief description of the event.
     * @param start       the starting date and time of event.
     * @param end         the ending date and time of event.
     * @param tags        a set of tags attached to the event.
     */
    public Event(boolean isDone, String description, String start, String end, Set<Tag> tags) {
        super(isDone, description, tags);
        this.start = LocalDateTime.parse(start, INPUT_DATE_TIME_FORMAT);
        this.end = LocalDateTime.parse(end, INPUT_DATE_TIME_FORMAT);
    }

    /**
     * Constructs an event which may or may not be completed
     * with a brief description and period of time.
     *
     * @param isDone      indicates if the event has been completed.
     * @param description a brief description of the event.
     * @param start       the starting date and time of event.
     * @param end         the ending date and time of event.
     * @param tags        a set of tags attached to the event.
     */
    public Event(boolean isDone, String description, LocalDateTime start, LocalDateTime end, Set<Tag> tags) {
        super(isDone, description, tags);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs an event which may or may not be completed
     * with a brief description, period of time, and a meeting link
     *
     * @param isDone      indicates if the event has been completed.
     * @param description a brief description of the event.
     * @param start       the starting date and time of event.
     * @param recurrence  the recurrence of event.
     * @param end         the ending date and time of event.
     * @param tags        a set of tags attached to the event.
     */
    public Event(boolean isDone, String description, LocalDateTime start,
                 LocalDateTime end, Recurrence recurrence, MeetingLink link, Set<Tag> tags) {
        super(isDone, description, tags);
        this.start = start;
        this.end = end;
        this.recurrence = recurrence;
        this.meetingLink = link;
    }

    /**
     * Constructs an event which may or may not be completed
     * with a brief description, period of time, and a meeting link
     * @param description a brief description of the event.
     * @param start       the starting date and time of event.
     * @param recurrence  the recurrence of event.
     * @param end         the ending date and time of event.
     * @param tags        a set of tags attached to the event.
     */
    public Event(String description, LocalDateTime start,
                 LocalDateTime end, Recurrence recurrence, MeetingLink link, Set<Tag> tags) {
        super(description, tags);
        this.start = start;
        this.end = end;
        this.recurrence = recurrence;
        this.meetingLink = link;
    }

    /**
     * Constructs an event which may or may not be completed
     * with a brief description, period of time, and a meeting link
     *
     * @param description a brief description of the event.
     * @param start       the starting date and time of event.
     * @param link        the Meeting Link of event.
     * @param end         the ending date and time of event.
     * @param tags        a set of tags attached to the event.
     */
    public Event(String description, String start, String end, Recurrence recurrence, MeetingLink link, Set<Tag> tags) {
        super(description, tags);
        this.start = LocalDateTime.parse(start, INPUT_DATE_TIME_FORMAT);
        this.end = LocalDateTime.parse(end, INPUT_DATE_TIME_FORMAT);
        this.recurrence = recurrence;
        this.meetingLink = link;
    }

    /**
     * Constructs an event which may or may not be completed
     * with a brief description, period of time, and a meeting link
     *
     * @param isDone      indicates if the event has been completed.
     * @param description a brief description of the event.
     * @param start       the starting date and time of event.
     * @param recurrence  the recurrence of event.
     * @param end         the ending date and time of event.
     * @param tags        a set of tags attached to the event.
     */
    public Event(boolean isDone, String description, LocalDateTime start,
                 LocalDateTime end, Recurrence recurrence, Set<Tag> tags) {
        super(isDone, description, tags);
        this.start = start;
        this.end = end;
        this.recurrence = recurrence;
    }

    /**
     * Constructs an event which may or may not be completed
     * with a brief description and period of time.
     *
     * @param description a brief description of the event.
     * @param start       the starting date and time of event (String).
     * @param end         the ending date and time of event (String).
     * @param recurrence  the recurrence of event.
     * @param tags        a set of tags attached to the event.
     */
    public Event(String description, String start, String end, Recurrence recurrence, Set<Tag> tags) {
        super(description, tags);
        this.start = LocalDateTime.parse(start, INPUT_DATE_TIME_FORMAT);
        this.end = LocalDateTime.parse(end, INPUT_DATE_TIME_FORMAT);
        this.recurrence = recurrence;
    }

    /**
     * Constructs an event which may or may not be completed
     * with a brief description and period of time.
     *
     * @param description a brief description of the event.
     * @param start       the starting date and time of event (LocalDateTime).
     * @param end         the ending date and time of event (LocalDateTime).
     * @param recurrence  the recurrence of event.
     * @param tags        a set of tags attached to the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end, Recurrence recurrence, Set<Tag> tags) {
        super(description, tags);
        this.start = start;
        this.end = end;
        this.recurrence = recurrence;
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
     * Returns the String representation of the start time which the event occurred over. In the
     * form of -start-.
     * Dates and times are in the format of inputFormatter.
     *
     * @return the String representation of the period of which the event starts.
     */
    public String getStartTime() {
        return this.start.format(INPUT_DATE_TIME_FORMAT).toString();
    }

    /**
     * Returns the String representation of the start time which the event occurred over. In the
     * form of -end-.
     * Dates and times are in the format of inputFormatter.
     *
     * @return the String representation of the period of which the event ends.
     */
    public String getEndTime() {
        return this.end.format(INPUT_DATE_TIME_FORMAT).toString();
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

    @Override
    public AddCommand markAsDone() {
        this.isDone = true;
        if (this.recurrence != null) {
            LocalDateTime newStartDateTime = this.getStart()
                    .plus(this.recurrence.getValue(), this.recurrence.getChronoUnit());
            LocalDateTime newEndDateTime = this.getEnd()
                    .plus(this.recurrence.getValue(), this.recurrence.getChronoUnit());

            AddCommand command;
            if (this.meetingLink == null) {
                command = new AddEventCommand(
                        new Event(description, newStartDateTime, newEndDateTime, recurrence, tags));
            } else {
                command = new AddEventCommand(
                        new Event(description, newStartDateTime, newEndDateTime,
                            recurrence, this.getMeetingLink(), tags));
            }
            return command;
        } else {
            return null;
        }
    }

    /**
     * Returns the string representation of the event, which includes the status icon, description, and period.
     *
     * @return the string representation of the event.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription() + " (at: " + getPeriod() + ") " + getTagsToString();
    }

    public String getDescriptionDateTime() {
        return this.description + " (at: " + getPeriod() + ")";
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
                    + " to " + this.end.format(INPUT_DATE_TIME_FORMAT) + " | " + this.getTagsToString();
        } else {
            return "E | 0 | " + this.getDescription() + " | " + this.start.format(INPUT_DATE_TIME_FORMAT)
                    + " to " + this.end.format(INPUT_DATE_TIME_FORMAT) + " | " + this.getTagsToString();
        }
    }

    @Override
    public LocalDateTime getDeadline() {
        return null;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getDateTime() {
        return this.getPeriod();
    }

    @Override
    public LocalDateTime getLocalDateTime() {
        return this.end;
    }

    public MeetingLink getMeetingLink() {
        return this.meetingLink;
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

    @Override
    public Optional<Link> getLink() {
        return Optional.ofNullable(this.meetingLink);
    }

    public String getStartDateTime() {
        return start.format(INPUT_DATE_TIME_FORMAT);
    }

    public String getEndDateTime() {
        return end.format(INPUT_DATE_TIME_FORMAT);
    }

    @Override
    public String getType() {
        return "Event";
    }

    @Override
    public Recurrence getRecurrence() {
        return this.recurrence;
    }
}


