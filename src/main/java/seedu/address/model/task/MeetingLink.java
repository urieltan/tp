package seedu.address.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MeetingLink {
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
    private LocalDateTime meetingTime;

    /** A brief description of the task. */
    private String description;

    /** A brief description of the task. */
    private String url;

    /**
     * Constructs a task that has not been completed
     * with a brief description and deadline for the task to be completed by.
     *
     * @param description a brief description of the deadline.
     * @param deadline    a String in a specific format (inputFormatter) which specifies a date.
     */
    public MeetingLink(String description, String url, String meetingTime) {
        this.description = description;
        this.url = url;
        this.meetingTime = LocalDateTime.parse(meetingTime, INPUT_DATE_TIME_FORMAT);
    }

    /**
     * Changes meeting time of this meeting.
     *
     * @param meetingTime the meeting Time
     */
    public void snooze(String meetingTime) {
        this.meetingTime = LocalDateTime.parse(meetingTime, INPUT_DATE_TIME_FORMAT);
    }

    /**
     * Returns a String representation of the meeting time with the format of outputFormatter.
     *
     * @return a String representation of the meeting time with the format of outputFormatter.
     */
    public String getMeetingTime() {
        return this.meetingTime.format(OUTPUT_DATE_TIME_FORMAT).toString();
    }

    /**
     * Returns a String representation of the url.
     *
     * @return a String representation of the url.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Returns a String representation of the task.
     * This representation includes the status icon, description, and deadline in the format of outputFormatter.
     *
     * @return a String representation of the task.
     */
    @Override
    public String toString() {
        return getDescription() + " " + getMeetingTime() + "\n" + getUrl();
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
        } else if (o instanceof MeetingLink) {
            MeetingLink link = (MeetingLink) o;
            boolean isEqual = this.description.equals(link.description)
                    && this.meetingTime.equals(link.meetingTime) && this.url.equals(link.url);
            return isEqual;
        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of the task in a format to be inputted into a text file for data storage.
     *
     * @return the string representation of the task to be saved in a text file.
     */
    public String saveFormat() {
        return this.description + this.meetingTime.format(INPUT_DATE_TIME_FORMAT).toString();
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getLocalDateTime() {
        return this.meetingTime;
    }

    public String getDescriptionDateTime() {
        return this.description + " (by: " + getMeetingTime() + ") (" + url + ")";
    }
}
