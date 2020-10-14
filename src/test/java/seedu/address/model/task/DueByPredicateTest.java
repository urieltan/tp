package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TodoBuilder;
import seedu.address.testutil.EventBuilder;

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
}
