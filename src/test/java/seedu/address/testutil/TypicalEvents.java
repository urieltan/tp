package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.task.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalEvents {
    public static final Event MEETING = new EventBuilder().withDescription("meeting")
            .withStartDateTime("12-12-2020 1000").withEndDateTime("12-12-2020 1130").build();
    public static final Event PARTY = new EventBuilder().withDescription("party")
            .withStartDateTime("01-01-2020 1800").withEndDateTime("02-01-2020 0600").build();

    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Events.
     */
    public static AddressBook getTypicalEventsAddressBook() {
        AddressBook ab = new AddressBook();
        for (Event Event: getTypicalEvents()) {
            ab.addEvent(Event);
        }
        return ab;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(MEETING, PARTY));
    }
}
