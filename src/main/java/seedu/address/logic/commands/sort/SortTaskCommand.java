package seedu.address.logic.commands.sort;

import java.util.Comparator;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDateComparator;

public class SortTaskCommand extends SortCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts tasks by date ";

    public static final String MESSAGE_SUCCESS = "Sorted tasks by date";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Comparator<Task> comparator = new TaskDateComparator();
        model.updateSortedTaskList(comparator);
        return new CommandResult(MESSAGE_SUCCESS, "TASK");
    }
}
