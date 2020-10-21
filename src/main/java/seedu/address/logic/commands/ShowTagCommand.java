package seedu.address.logic.commands;

/**
 * Show all items whose tag matches the tag keyword.
 * Keyword matching is case insensitive.
 */
public abstract class ShowTagCommand extends Command {
    public static final String COMMAND_WORD = "show";
}
