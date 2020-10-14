package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.TodoBuilder;

public class DueByPredicateTest {
    @Test
    public void test_tasksDueBy_returnsTrue() {
        DueByPredicate predicate = new DueByPredicate("12-12-2020 2359");

        //To-do (homework)
        assertTrue(predicate.test(new TodoBuilder().build()));

        //Event (meeting)
        predicate = new DueByPredicate("12-12-2020 1130");
        assertTrue(predicate.test(new EventBuilder().build()));
    }

    @Test
    public void test_noTasksDueBy_returnsFalse() {
        DueByPredicate predicate = new DueByPredicate("15-12-2020 2359");

        //To-do
        assertFalse(predicate.test(new TodoBuilder().build()));

        //Event
        assertFalse(predicate.test(new EventBuilder().build()));
    }

    @Test
    public void equals() {
        String firstDateTime = "10-10-2010 1010";
        String secondDateTime = "20-12-2020 2359";

        DueByPredicate firstPredicate = new DueByPredicate(firstDateTime);
        DueByPredicate secondPredicate = new DueByPredicate(secondDateTime);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DueByPredicate firstPredicateCopy = new DueByPredicate(firstDateTime);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
