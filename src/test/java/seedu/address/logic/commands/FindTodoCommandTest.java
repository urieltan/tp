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

import seedu.address.logic.commands.find.FindTodoCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.DescriptionContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindTodoCommandTest {
    private Model model = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalTodosTaskList());
    private Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalTodosTaskList());

    @Test
    public void equals() {
        DescriptionContainsKeywordsPredicate firstPredicate =
            new DescriptionContainsKeywordsPredicate(Collections.singletonList("first"));
        DescriptionContainsKeywordsPredicate secondPredicate =
            new DescriptionContainsKeywordsPredicate(Collections.singletonList("second"));

        FindTodoCommand findFirstCommand = new FindTodoCommand(firstPredicate);
        FindTodoCommand findSecondCommand = new FindTodoCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindTodoCommand findFirstCommandCopy = new FindTodoCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTodoFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        DescriptionContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindTodoCommand command = new FindTodoCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "TASK", expectedModel);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_multipleKeywords_multipleTodosFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        DescriptionContainsKeywordsPredicate predicate = preparePredicate("update assignment");
        FindTodoCommand command = new FindTodoCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "TASK", expectedModel);
        assertEquals(Arrays.asList(ASSIGNMENT, USER_GUIDE, DEVELOPER_GUIDE), expectedModel.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code DescriptionContainsKeywordsPredicate}.
     */
    private DescriptionContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DescriptionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
