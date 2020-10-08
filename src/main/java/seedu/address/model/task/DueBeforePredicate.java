package seedu.address.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

public class DueBeforePredicate implements Predicate<Task> {
    private final LocalDateTime deadline;

    public DueBeforePredicate(String strDeadline) {
        DateTimeFormatter INPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime deadline = LocalDateTime.parse(strDeadline, INPUT_DATE_TIME_FORMAT);
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
