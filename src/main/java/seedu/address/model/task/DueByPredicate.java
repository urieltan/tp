package seedu.address.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

public class DueByPredicate implements Predicate<Task> {
    private final LocalDateTime deadline;

    public DueByPredicate(String strDeadline) {
        DateTimeFormatter INPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime deadline = LocalDateTime.parse(strDeadline, INPUT_DATE_TIME_FORMAT);
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
