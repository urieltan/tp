package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTodos.ASSIGNMENT;
import static seedu.address.testutil.TypicalTodos.DEVELOPER_GUIDE;
import static seedu.address.testutil.TypicalTodos.USER_GUIDE;
import static seedu.address.testutil.TypicalTodos.getTypicalTodosTaskList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.showtag.ShowTagTodoCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskTagMatchesKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ShowTagTodoCommand}.
 */
public class ShowTagTodoCommandTest {
    private Model model = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalTodosTaskList());
    private Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalTodosTaskList());

    @Test
    public void equals() {
        TaskTagMatchesKeywordPredicate firstPredicate =
            new TaskTagMatchesKeywordPredicate("first");
        TaskTagMatchesKeywordPredicate secondPredicate =
            new TaskTagMatchesKeywordPredicate("second");

        ShowTagTodoCommand showTagFirstCommand = new ShowTagTodoCommand(firstPredicate);
        ShowTagTodoCommand showTagSecondCommand = new ShowTagTodoCommand(secondPredicate);

        // same object -> returns true
        assertTrue(showTagFirstCommand.equals(showTagFirstCommand));

        // same values -> returns true
        ShowTagTodoCommand showTagFirstCommandCopy = new ShowTagTodoCommand(firstPredicate);
        assertTrue(showTagFirstCommand.equals(showTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(showTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showTagFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(showTagFirstCommand.equals(showTagSecondCommand));
    }

    @Test
    public void execute_notExistingTag_noTodoFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskTagMatchesKeywordPredicate predicate = preparePredicate("Clown");
        ShowTagTodoCommand command = new ShowTagTodoCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "TASK", expectedModel);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_insesitiveCaseTag_todoFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        TaskTagMatchesKeywordPredicate predicate = preparePredicate("Cs2100");
        ShowTagTodoCommand command = new ShowTagTodoCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "TASK", expectedModel);
        assertEquals(Arrays.asList(ASSIGNMENT), expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_existingTag_multipleTodosFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        TaskTagMatchesKeywordPredicate predicate = preparePredicate("cs2103t");
        ShowTagTodoCommand command = new ShowTagTodoCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "TASK", expectedModel);
        assertEquals(Arrays.asList(USER_GUIDE, DEVELOPER_GUIDE), expectedModel.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code TaskTagMatchesKeywordPredicate}.
     */
    private TaskTagMatchesKeywordPredicate preparePredicate(String userInput) {
        return new TaskTagMatchesKeywordPredicate(userInput);
    }
}
