package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.delete.DeleteTodoCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Todo;
import seedu.address.testutil.TodoBuilder;

public class DeleteTodoCommandTest {
    private Todo todo = new TodoBuilder().build();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());

    @Test
    public void execute_validIndexTaskList_success() {
        model.addTodo(todo);
        Todo todoToDelete = (Todo) model.getFilteredTaskList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteTodoCommand deleteTodoCommand = new DeleteTodoCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteTodoCommand.MESSAGE_DELETE_TODO_SUCCESS, todoToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new TaskList());

        assertCommandSuccess(deleteTodoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexTaskList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DeleteTodoCommand deleteTodoCommand = new DeleteTodoCommand(outOfBoundIndex);

        assertCommandFailure(deleteTodoCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTodoCommand deleteFirstCommand = new DeleteTodoCommand(INDEX_FIRST_PERSON);
        DeleteTodoCommand deleteSecondCommand = new DeleteTodoCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTodoCommand deleteFirstCommandCopy = new DeleteTodoCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    //Todo
    // Implement this after implementing "show" command for To-do
    // 1) public void execute_validIndexFilteredTaskList_success()
    // 2) public void execute_invalidIndexFilteredTaskList_throwsCommandException()
    // 3) private void showNoTodo(Model model)
}
