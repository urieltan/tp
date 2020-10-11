package seedu.address.model.task;

/**
 * Checks for every task (To-do and event) that is due before the given date and time.
 */
public class DueBeforePredicate extends DuePredicate {
    public DueBeforePredicate(String strDeadline) {
        super(strDeadline);
    }

    @Override
    public boolean test(Task task) {
        return task.getLocalDateTime().isAfter(deadline);
    }
}
