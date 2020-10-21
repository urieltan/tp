package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.LECTURE;
import static seedu.address.testutil.TypicalEvents.MEETING;
import static seedu.address.testutil.TypicalEvents.WORKSHOP;
import static seedu.address.testutil.TypicalEvents.getTypicalEventsTaskList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.showtag.ShowTagEventCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskTagMatchesKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ShowTagEventCommand}.
 */
public class ShowTagEventCommandTest {
    private Model model = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalEventsTaskList());
    private Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalEventsTaskList());

    @Test
    public void equals() {
        TaskTagMatchesKeywordPredicate firstPredicate =
            new TaskTagMatchesKeywordPredicate("first");
        TaskTagMatchesKeywordPredicate secondPredicate =
            new TaskTagMatchesKeywordPredicate("second");

        ShowTagEventCommand showTagFirstCommand = new ShowTagEventCommand(firstPredicate);
        ShowTagEventCommand showTagSecondCommand = new ShowTagEventCommand(secondPredicate);

        // same object -> returns true
        assertTrue(showTagFirstCommand.equals(showTagFirstCommand));

        // same values -> returns true
        ShowTagEventCommand showTagFirstCommandCopy = new ShowTagEventCommand(firstPredicate);
        assertTrue(showTagFirstCommand.equals(showTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(showTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showTagFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(showTagFirstCommand.equals(showTagSecondCommand));
    }

    @Test
    public void execute_notExistingTag_noEventFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskTagMatchesKeywordPredicate predicate = preparePredicate("Clown");
        ShowTagEventCommand command = new ShowTagEventCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "TASK", expectedModel);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_insesitiveCaseTag_eventFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        TaskTagMatchesKeywordPredicate predicate = preparePredicate("workSHoP");
        ShowTagEventCommand command = new ShowTagEventCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "TASK", expectedModel);
        assertEquals(Arrays.asList(WORKSHOP), expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_existingTag_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        TaskTagMatchesKeywordPredicate predicate = preparePredicate("cs2103t");
        ShowTagEventCommand command = new ShowTagEventCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "TASK", expectedModel);
        assertEquals(Arrays.asList(MEETING, LECTURE), expectedModel.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code TaskTagMatchesKeywordPredicate}.
     */
    private TaskTagMatchesKeywordPredicate preparePredicate(String userInput) {
        return new TaskTagMatchesKeywordPredicate(userInput);
    }
}
