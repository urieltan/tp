package seedu.address.logic.commands;

public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add an item to lifebook."
            + "Parameters: todo|event|contact|meeting\n"
            + "add [todo|event|contact|meeting] /h to see more.";
}
