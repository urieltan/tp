package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEvents.MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.add.AddEventCommand;
import seedu.address.model.task.Event;
import seedu.address.testutil.EventBuilder;

public class AddEventCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();
    private static final String MEETING_DESC = "desc/meeting ";
    private static final String MEETING_START_DATE = "startdate/12-12-2020 ";
    private static final String MEETING_START_TIME = "starttime/1000 ";
    private static final String MEETING_END_DATE = "enddate/12-12-2020 ";
    private static final String MEETING_END_TIME = "endtime/1130";

    private static final String INVALID_DATE = "date/1-1-2020 ";
    private static final String INVALID_TIME = "time/12000 ";

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder(MEETING).build();

        assertParseSuccess(parser, "event " + MEETING_DESC + MEETING_START_DATE + MEETING_START_TIME
                + MEETING_END_DATE + MEETING_END_TIME, new AddEventCommand(expectedEvent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // missing description
        assertParseFailure(parser, "event " + MEETING_START_DATE + MEETING_START_TIME
                + MEETING_END_DATE + MEETING_END_TIME, expectedMessage);

        // missing date
        assertParseFailure(parser, "event " + MEETING_DESC + MEETING_START_TIME
                + MEETING_END_TIME, expectedMessage);

        // missing time
        assertParseFailure(parser, "event " + MEETING_DESC + MEETING_START_TIME
                + MEETING_END_TIME, expectedMessage);

        // missing start date and time
        assertParseFailure(parser, "event " + MEETING_DESC + MEETING_END_DATE
                + MEETING_END_TIME, expectedMessage);

        // missing end date and time
        assertParseFailure(parser, "event " + MEETING_DESC + MEETING_START_DATE
                + MEETING_START_TIME, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // invalid date
        assertParseFailure(parser, "event " + MEETING_DESC + INVALID_DATE + MEETING_START_TIME
                        + MEETING_END_DATE + MEETING_END_TIME, expectedMessage);

        //Todo
        // invalid time (after implementing exceptions for wrong time format)
        //assertParseFailure(parser, "Event " + MEETING_DESC + MEETING_DATE + INVALID_TIME,
        //        expectedMessage);
    }

}
