package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.LinkCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.MeetingLink;

/**
 * Add a meeting link to a event.
 */
public class LinkMeetingCommand extends LinkCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add meeting link to event. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_URL + "URL "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";

    private final MeetingLink meetingLink;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public LinkMeetingCommand(MeetingLink meetingLink) {
        requireNonNull(meetingLink);
        this.meetingLink = meetingLink;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.addTodo(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getDescriptionDateTime()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTodoCommand // instanceof handles nulls
                && toAdd.equals(((AddTodoCommand) other).toAdd));
    }

}
}