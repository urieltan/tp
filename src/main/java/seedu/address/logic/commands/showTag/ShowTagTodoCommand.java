package seedu.address.logic.commands.showTag;
import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ShowTagCommand;
import seedu.address.model.Model;
import seedu.address.model.task.TaskTagMatchesKeywordPredicate;

/**
 * Show all todos whose tag matches the tag keyword.
 * Keyword matching is case insensitive.
 */
public class ShowTagTodoCommand extends ShowTagCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " todo" + ": Shows todos whose tags match "
        + "the specified tag keyword (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: t/TAG\n"
        + "Example: " + COMMAND_WORD + " todo t/CS2100";

    private final TaskTagMatchesKeywordPredicate predicate;

    public ShowTagTodoCommand(TaskTagMatchesKeywordPredicate predicate) {
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
            || (other instanceof ShowTagTodoCommand // instanceof handles nulls
            && predicate.equals(((ShowTagTodoCommand) other).predicate)); // state check
    }
}

