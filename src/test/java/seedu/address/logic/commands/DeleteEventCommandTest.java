package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.delete.DeleteEventCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Event;
import seedu.address.testutil.EventBuilder;

import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class DeleteEventCommandTest {
    private Event Event = new EventBuilder().build();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());

    @Test
    public void execute_validIndexTaskList_success() {
        model.addEvent(Event);
        Event EventToDelete = (Event) model.getFilteredTaskList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteEventCommand.MESSAGE_DELETE_PERSON_SUCCESS, EventToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new TaskList());

        assertCommandSuccess(deleteEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexTaskList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(outOfBoundIndex);

        assertCommandFailure(deleteEventCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteEventCommand deleteFirstCommand = new DeleteEventCommand(INDEX_FIRST_PERSON);
        DeleteEventCommand deleteSecondCommand = new DeleteEventCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteEventCommand deleteFirstCommandCopy = new DeleteEventCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    //Todo
    // Implement this after implementing "show" command for Event
    // 1) public void execute_validIndexFilteredTaskList_success()
    // 2) public void execute_invalidIndexFilteredTaskList_throwsCommandException()
    // 3) private void showNoEvent(Model model)
}
