package seedu.address.logic.commands.list;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListEventCommand extends ListCommand {

    public static final String MESSAGE_SUCCESS = "Listed all events";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskListEvent();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
