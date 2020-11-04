package seedu.address.logic.commands;

import seedu.address.logic.commands.link.LinkCollaborativeCommand;
import seedu.address.logic.commands.link.LinkMeetingCommand;

public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Find todos, events or contacts.\n"
            + "Parameters: [todo|contact|event]"
            + COMMAND_WORD + " [todo|event|contact] /h to see more.";
}
