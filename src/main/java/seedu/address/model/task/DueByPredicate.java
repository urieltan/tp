package seedu.address.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

/**
 * Checks for every task (To-do and event) that is due by the given date and time.
 */
public class DueByPredicate implements Predicate<Task> {
    private final LocalDateTime deadline;

    /**
     * Converts deadline into a LocalDateTime type.
     *
     * @param strDeadline string format of date + time
     */
    public DueByPredicate(String strDeadline) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime deadline = LocalDateTime.parse(strDeadline, dateTimeFormat);
        this.deadline = deadline;
    }

    @Override
    public boolean test(Task task) {
        return task.getLocalDateTime().isEqual(this.deadline);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DueByPredicate // instanceof handles nulls
                && deadline.equals(((DueByPredicate) other).deadline)); // state check
    }
}
