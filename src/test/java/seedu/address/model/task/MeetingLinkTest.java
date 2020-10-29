package seedu.address.model.task;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.MeetingLinkBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TUTOR_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TIME_TUTOR_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_TUTOR_MEETING;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLinks.PROJECT_DOCUMENT;
import static seedu.address.testutil.TypicalLinks.PROJECT_MEETING;
import static seedu.address.testutil.TypicalLinks.TUTOR_MEETING;

public class MeetingLinkTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetingLink(null, null, (String) null));
    }

    @Test
    public void constructor_invalidURL_throwsIllegalArgumentException() {
        String invalidUrl = "example.com";
        assertThrows(IllegalArgumentException.class, () -> new MeetingLink(VALID_DESCRIPTION_TUTOR_MEETING,
                invalidUrl, VALID_DATE_TIME_TUTOR_MEETING));
    }

    @Test
    public void equals() {

        // same values -> returns true
        MeetingLink projectMeetingCopy = new MeetingLinkBuilder(PROJECT_MEETING).build();
        assertTrue(PROJECT_MEETING.equals(projectMeetingCopy));

        // same object -> returns true
        assertTrue(PROJECT_MEETING.equals(PROJECT_MEETING));

        // null -> returns false
        assertFalse(PROJECT_MEETING.equals(null));

        // different type -> returns false
        assertFalse(PROJECT_MEETING.equals(5));

        assertFalse(PROJECT_MEETING.equals(PROJECT_DOCUMENT));

        // different person -> returns false
        assertFalse(PROJECT_MEETING.equals(TUTOR_MEETING));

        // different description -> returns false
        MeetingLink editedProjectMeeting = new MeetingLinkBuilder(PROJECT_MEETING)
                .withDescription(VALID_DESCRIPTION_TUTOR_MEETING).build();
        assertFalse(PROJECT_MEETING.equals(editedProjectMeeting));

        // different datetime -> returns false
        editedProjectMeeting = new MeetingLinkBuilder(PROJECT_MEETING)
                .withDatetime(VALID_DATE_TIME_TUTOR_MEETING).build();
        assertFalse(PROJECT_MEETING.equals(editedProjectMeeting));

        // different URL -> returns false
        editedProjectMeeting = new MeetingLinkBuilder(PROJECT_MEETING)
                .withUrl(VALID_URL_TUTOR_MEETING).build();
        assertFalse(PROJECT_MEETING.equals(editedProjectMeeting));
    }
}
