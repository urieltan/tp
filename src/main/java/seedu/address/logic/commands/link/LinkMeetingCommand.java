package seedu.address.logic.commands.link;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LinkCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;
import seedu.address.model.task.Event;
import seedu.address.model.task.MeetingLink;

/**
 * Add a meeting link to a event.
 */
public class LinkMeetingCommand extends LinkCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add meeting link to event. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_URL + "URL "
            + PREFIX_URL + "URL "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";

    private final MeetingLink meetingLink;

    private final Index index;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public LinkMeetingCommand(Index index, MeetingLink meetingLink) {
        requireNonNull(meetingLink);
        this.meetingLink = meetingLink;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Task> lastShownList = model.getFilteredTaskList();

        if(index.getZeroBased() >= lastShownList.size()){
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        try {
            Event eventToEdit = (Event) lastShownList.get(index.getZeroBased());
            Event editedEvent = new Event(eventToEdit.getDescription(), eventToEdit.getStartTime(),
                    eventToEdit.getEndTime(), meetingLink);
            model.setTask(eventToEdit, editedEvent);
            model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);
        }
        catch (ClassCastException e){
            throw new CommandException(Messages.MESSAGE_TASK_IS_NOT_EVENT);
        }


        return new CommandResult(String.format(MESSAGE_SUCCESS, meetingLink.getDescriptionDateTime()),"TASK");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkMeetingCommand // instanceof handles nulls
                && meetingLink.equals(((LinkMeetingCommand) other).meetingLink));
    }
}
