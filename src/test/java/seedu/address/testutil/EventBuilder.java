package seedu.address.testutil;

import seedu.address.model.task.Event;

/**
 * A utility class to help with building To-do objects.
 */
public class EventBuilder {

    public static final String DEFAULT_DESC = "meeting";
    public static final String DEFAULT_STARTDATETIME = "12-12-2020 1000";
    public static final String DEFAULT_ENDDATETIME = "12-12-2020 1130";

    private String description;
    private String startDateTime;
    private String endDateTime;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        description = DEFAULT_DESC;
        startDateTime = DEFAULT_STARTDATETIME;
        endDateTime = DEFAULT_ENDDATETIME;
    }

    /**
     * Initializes the EventBuilder with the data of {@code EventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        description = eventToCopy.getDescription();
        startDateTime = eventToCopy.getStartDateTime();
        endDateTime = eventToCopy.getEndDateTime();
    }

    /**
     * Sets the {@code Description} of the {@code To-do} that we are building.
     */
    public EventBuilder withDescription(String desc) {
        this.description = desc;
        return this;
    }


    public Event build() {
        return new Event(description, startDateTime, endDateTime);
    }

}
