package seedu.address.logic.commands.sort;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class SortClearCommand extends SortCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all sorting ";

    public static final String MESSAGE_SUCCESS = "All sorting cleared";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateSortedPersonList(null);
        model.updateSortedTaskList(null);
        return new CommandResult(MESSAGE_SUCCESS, "CONTACT");
    }
}
