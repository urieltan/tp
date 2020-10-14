package seedu.address.logic.commands.list;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.Model;
import seedu.address.model.task.TaskTypeMatchesKeywordsPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListTodoCommand extends ListCommand {

    public static final String MESSAGE_SUCCESS = "Listed all todo";
    public static final String TODO_KEYWORD = "Todo";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<String> keyword = new ArrayList<>();
        keyword.add(TODO_KEYWORD);
        model.updateFilteredTaskList(new TaskTypeMatchesKeywordsPredicate(keyword));
        return new CommandResult(MESSAGE_SUCCESS, "TASK");
    }
}
