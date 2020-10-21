package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public abstract class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort items of lifebook."
            + "Parameters: contact|task|clear\n"
            + "sort [contact|task|clear] /h to see more.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
