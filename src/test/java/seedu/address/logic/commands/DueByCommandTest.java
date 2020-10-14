package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.due.DueByCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.DueByPredicate;
import seedu.address.model.task.Todo;
import seedu.address.testutil.TodoBuilder;

public class DueByCommandTest {
    private Todo todo = new TodoBuilder().build();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());

    @Test
    public void execute_rightDateAndTimeInputs_todoFound() {
        model.addTodo(todo);

        DueByPredicate predicate = preparePredicate("date/13-12-2020 time/2359");
        DueByCommand command = new DueByCommand(predicate);

        String expectedMessage = String.format(DueByCommand.MESSAGE_SUCCESS, predicate.getDateTime());

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());

        assertCommandSuccess(command, model, expectedMessage, "TASK", expectedModel);
    }

    @Test
    public void equals() {
        DueByPredicate firstPredicate = preparePredicate("date/10-10-2020 time/0000");
        DueByPredicate secondPredicate = preparePredicate("date/30-12-2021 time/2100");

        DueByCommand firstCommand = new DueByCommand(firstPredicate);
        DueByCommand secondCommand = new DueByCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        DueByCommand firstCommandCopy = new DueByCommand(firstPredicate);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    /**
     * Parses {@code userInput} into a {@code DueByPredicate}.
     */
    private DueByPredicate preparePredicate(String userInput) {
        String deadline = userInput.replace("date/", "");
        deadline = deadline.replace("time/", "");
        return new DueByPredicate(deadline);
    }
}
