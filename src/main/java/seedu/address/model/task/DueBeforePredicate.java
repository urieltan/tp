package seedu.address.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

/**
 * Checks for every task (To-do and event) that is due before the given date and time.
 */
public class DueBeforePredicate implements Predicate<Task> {
    private final LocalDateTime deadline;

    /**
     * Converts deadline into a LocalDateTime type.
     *
     * @param strDeadline string format of date + time
     */
    public DueBeforePredicate(String strDeadline) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime deadline = LocalDateTime.parse(strDeadline, dateTimeFormat);
        this.deadline = deadline;
    }

    @Override
    public boolean test(Task task) {
        return task.getLocalDateTime().isAfter(this.deadline);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DueBeforePredicate // instanceof handles nulls
                && deadline.equals(((DueBeforePredicate) other).deadline)); // state check
    }
}
