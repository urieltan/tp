package seedu.address.logic.commands.showtag;
import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ShowTagCommand;
import seedu.address.model.Model;
import seedu.address.model.task.TaskTagMatchesKeywordPredicate;

/**
 * Show all events whose tag matches the tag keyword.
 * Keyword matching is case insensitive.
 */
public class ShowTagEventCommand extends ShowTagCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " event" + ": Shows events whose tags match "
        + "the specified tag keyword (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: t/TAG\n"
        + "Example: " + COMMAND_WORD + " event t/CS2100";

    private final TaskTagMatchesKeywordPredicate predicate;

    public ShowTagEventCommand(TaskTagMatchesKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()), "TASK");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ShowTagEventCommand // instanceof handles nulls
            && predicate.equals(((ShowTagEventCommand) other).predicate)); // state check
    }
}

