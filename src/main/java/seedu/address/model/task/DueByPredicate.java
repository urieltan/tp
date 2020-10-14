package seedu.address.model.task;

/**
 * Checks for every task (To-do and event) that is due by the given date and time.
 */
public class DueByPredicate extends DuePredicate {
    public DueByPredicate(String strDeadline) {
        super(strDeadline);
    }

    @Override
    public boolean test(Task task) {
        return task.getLocalDateTime().isEqual(deadline);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DueByPredicate // instanceof handles nulls
                && deadline.equals(((DueByPredicate) other).deadline)); // state check
    }
}
