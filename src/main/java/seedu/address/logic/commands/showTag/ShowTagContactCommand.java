package seedu.address.logic.commands.showTag;
import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ShowTagCommand;
import seedu.address.model.Model;
import seedu.address.model.person.ContactTagMatchesKeywordPredicate;

/**
 * Show all contacts whose tag matches the tag keyword.
 * Keyword matching is case insensitive.
 */
public class ShowTagContactCommand extends ShowTagCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " contact" + ": Shows contacts whose tags match "
        + "the specified tag keyword (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: t/TAG\n"
        + "Example: " + COMMAND_WORD + " contact t/friends";

    private final ContactTagMatchesKeywordPredicate predicate;

    public ShowTagContactCommand(ContactTagMatchesKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()), "CONTACT");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ShowTagContactCommand // instanceof handles nulls
            && predicate.equals(((ShowTagContactCommand) other).predicate)); // state check
    }
}

