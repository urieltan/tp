package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ContactTagMatchesKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ShowTagCommand}.
 */
public class ShowTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());

    @Test
    public void equals() {
        ContactTagMatchesKeywordPredicate firstPredicate =
            new ContactTagMatchesKeywordPredicate("first");
        ContactTagMatchesKeywordPredicate secondPredicate =
            new ContactTagMatchesKeywordPredicate("second");

        ShowTagCommand showTagFirstCommand = new ShowTagCommand(firstPredicate);
        ShowTagCommand showTagSecondCommand = new ShowTagCommand(secondPredicate);

        // same object -> returns true
        assertTrue(showTagFirstCommand.equals(showTagFirstCommand));

        // same values -> returns true
        ShowTagCommand showTagFirstCommandCopy = new ShowTagCommand(firstPredicate);
        assertTrue(showTagFirstCommand.equals(showTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(showTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showTagFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(showTagFirstCommand.equals(showTagSecondCommand));
    }

    @Test
    public void execute_notExistingTag_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ContactTagMatchesKeywordPredicate predicate = preparePredicate("Clown");
        ShowTagCommand command = new ShowTagCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "CONTACT", expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_insesitiveCaseTag_personsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        ContactTagMatchesKeywordPredicate predicate = preparePredicate("OWESmOnEY");
        ShowTagCommand command = new ShowTagCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "CONTACT", expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_existingTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ContactTagMatchesKeywordPredicate predicate = preparePredicate("friends");
        ShowTagCommand command = new ShowTagCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "CONTACT", expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code ContactTagMatchesKeywordPredicate}.
     */
    private ContactTagMatchesKeywordPredicate preparePredicate(String userInput) {
        return new ContactTagMatchesKeywordPredicate(userInput);
    }
}
