package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.TagMatchesKeywordPredicate;

/**
 * Show all contacts whose tag matches the tag keyword.
 * Keyword matching is case insensitive.
 */
public class ShowTagCommand extends Command {
    public static final String COMMAND_WORD = "show";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows contacts whose tags match "
        + "the specified tag keyword (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: t/TAG\n"
        + "Example: " + COMMAND_WORD + " t/friends";

    private final TagMatchesKeywordPredicate predicate;

    public ShowTagCommand(TagMatchesKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ShowTagCommand // instanceof handles nulls
            && predicate.equals(((ShowTagCommand) other).predicate)); // state check
    }
}
