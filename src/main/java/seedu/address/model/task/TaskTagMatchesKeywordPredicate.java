package seedu.address.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Tag} matches the tag keyword given.
 */
public class TaskTagMatchesKeywordPredicate implements Predicate<Task> {
    private final String keyword;

    public TaskTagMatchesKeywordPredicate(String keyword) {
        // make sure keyword is only one word
        assert !keyword.contains("\\s+");
        this.keyword = keyword;
    }

    @Override
    public boolean test(Task task) {
        return task.getTags().stream()
            .anyMatch(keyword -> this.keyword.trim().toLowerCase().equals(keyword.tagName.trim().toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TaskTagMatchesKeywordPredicate // instanceof handles nulls
            && keyword.equals(((TaskTagMatchesKeywordPredicate) other).keyword)); // state check
    }
}
