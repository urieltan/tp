package seedu.address.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MeetingLink extends Link {
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

    public MeetingLink() {
        this("No meeting link", "-", "10-10-2020 2000");
    }

    /**
     * Constructs a Meeting Link
     * with a brief description, url, and meeting time.
     *
     * @param description a brief description of the deadline.
     * @param url    a String in a URL format which specifies the link.
     * @param meetingTime a String which describes the meeting time.
     */
    public MeetingLink(String description, String url, String meetingTime) {
        super(description, url);
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
     * Returns a String representation of the Meeting link.
     * This representation includes the description, meeting time, and url in the format of outputFormatter.
     *
     * @return a String representation of the Meeting Link.
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
            boolean isEqual = this.getDescription().equals(link.getDescription())
                    && this.meetingTime.equals(link.meetingTime) && this.getUrl().equals(link.getUrl());
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
        return this.getDescription() + this.meetingTime.format(INPUT_DATE_TIME_FORMAT).toString();
    }

    public LocalDateTime getLocalDateTime() {
        return this.meetingTime;
    }

    @Override
    public String getDescription() {
        if (super.getDescription().equals("No meeting link")) {
            return super.getDescription();
        } else {
            return this.getDescriptionDateTime();
        }
    }

    public String getDescriptionDateTime() {
        return super.getDescription() + " (on: " + getMeetingTime() + ")";
    }
}
