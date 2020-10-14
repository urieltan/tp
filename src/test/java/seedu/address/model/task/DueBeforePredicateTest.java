package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TodoBuilder;
import seedu.address.testutil.EventBuilder;

public class DueBeforePredicateTest {
    @Test
    public void test_tasksDueBefore_returnsTrue() {
        DueBeforePredicate predicate = new DueBeforePredicate("10-12-2020 2359");

        //To-do
        assertTrue(predicate.test(new TodoBuilder().build()));

        //Event
        assertTrue(predicate.test(new EventBuilder().build()));
    }

    @Test
    public void test_noTasksDueBefore_returnsFalse() {
        DueBeforePredicate predicate = new DueBeforePredicate("15-12-2020 2359");

        //To-do
        assertFalse(predicate.test(new TodoBuilder().build()));

        //Event
        assertFalse(predicate.test(new EventBuilder().build()));
    }
}
