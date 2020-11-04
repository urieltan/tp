package seedu.address.logic.commands;

import seedu.address.logic.commands.link.LinkCollaborativeCommand;
import seedu.address.logic.commands.link.LinkMeetingCommand;

/**
 * Show all items whose tag matches the tag keyword.
 * Keyword matching is case insensitive.
 */
public abstract class ShowTagCommand extends Command {
    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": show a list of contacts/events/todos"
            + " associated with the tag\n"
            + "Parameters: [contact|todo|event] t/TAG";
}
