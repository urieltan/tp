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

import seedu.address.logic.commands.showtag.ShowTagContactCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ContactTagMatchesKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ShowTagContactCommand}.
 */
public class ShowTagContactCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());

    @Test
    public void equals() {
        ContactTagMatchesKeywordPredicate firstPredicate =
            new ContactTagMatchesKeywordPredicate("first");
        ContactTagMatchesKeywordPredicate secondPredicate =
            new ContactTagMatchesKeywordPredicate("second");

        ShowTagContactCommand showTagFirstCommand = new ShowTagContactCommand(firstPredicate);
        ShowTagContactCommand showTagSecondCommand = new ShowTagContactCommand(secondPredicate);

        // same object -> returns true
        assertTrue(showTagFirstCommand.equals(showTagFirstCommand));

        // same values -> returns true
        ShowTagContactCommand showTagFirstCommandCopy = new ShowTagContactCommand(firstPredicate);
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
        ShowTagContactCommand command = new ShowTagContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "CONTACT", expectedModel);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_insesitiveCaseTag_personsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        ContactTagMatchesKeywordPredicate predicate = preparePredicate("OWESmOnEY");
        ShowTagContactCommand command = new ShowTagContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "CONTACT", expectedModel);
        assertEquals(Arrays.asList(BENSON), expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_existingTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ContactTagMatchesKeywordPredicate predicate = preparePredicate("friends");
        ShowTagContactCommand command = new ShowTagContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "CONTACT", expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), expectedModel.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code ContactTagMatchesKeywordPredicate}.
     */
    private ContactTagMatchesKeywordPredicate preparePredicate(String userInput) {
        return new ContactTagMatchesKeywordPredicate(userInput);
    }
}
