package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Tag} matches the tag keyword given.
 */
public class ContactTagMatchesKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    /**
     * Construct a predicate to match keyword tag to contact's tag
     * @param keyword the keyword for the tag
     */
    public ContactTagMatchesKeywordPredicate(String keyword) {
        // make sure keyword is only one word
        assert !keyword.contains("\\s+");
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
            || (other instanceof ContactTagMatchesKeywordPredicate // instanceof handles nulls
            && keyword.equals(((ContactTagMatchesKeywordPredicate) other).keyword)); // state check
    }
}
