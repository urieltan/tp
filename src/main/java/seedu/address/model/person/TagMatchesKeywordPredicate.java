package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Tag} matches the tag keyword given.
 */
public class TagMatchesKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public TagMatchesKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().stream()
            .anyMatch(keyword -> this.keyword.trim().toLowerCase().equals(keyword.tagName.trim().toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TagMatchesKeywordPredicate // instanceof handles nulls
            && keyword.equals(((TagMatchesKeywordPredicate) other).keyword)); // state check
    }
}
