package seedu.address.logic.commands;

public abstract class LinkCommand extends Command {

    public static final String COMMAND_WORD = "link";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a link to your todo or event"
            + "Parameters: [meet|doc]\n"
            + "link [meet|doc] /h to see more.";
}
