package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public abstract class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort items of lifebook.\n"
            + "Parameters: [contact|task|clear]\n"
            + "Usage: " + COMMAND_WORD + " [contact|task|clear]\n"
            + "Example: " + COMMAND_WORD + " task";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand);
    }
}
