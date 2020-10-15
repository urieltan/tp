package seedu.address.logic.commands.link;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.LinkCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.CollaborativeLink;
import seedu.address.model.task.Task;
import seedu.address.model.task.Todo;

/**
 * Add a Collaborative link to a todo.
 */
public class LinkCollaborativeCommand extends LinkCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add collaborative link to todo. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_URL + "URL "
            + PREFIX_INDEX + "INDEX ";

    public static final String MESSAGE_SUCCESS = "New collaborative folder link added: %1$s";

    private final CollaborativeLink collaborativeLink;

    private final Index index;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public LinkCollaborativeCommand(Index index, CollaborativeLink collaborativeLink) {
        requireNonNull(collaborativeLink);
        this.collaborativeLink = collaborativeLink;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        try {
            Todo todoToEdit = (Todo) lastShownList.get(index.getZeroBased());
            Todo editedTodo = new Todo(todoToEdit.getDescription(), todoToEdit.getDeadline(), collaborativeLink);
            model.setTask(todoToEdit, editedTodo);
            model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);
        } catch (ClassCastException e) {
            throw new CommandException(Messages.MESSAGE_TASK_IS_NOT_EVENT);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, collaborativeLink.getDescription()), "TASK");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkCollaborativeCommand // instanceof handles nulls
                && collaborativeLink.equals(((LinkCollaborativeCommand) other).collaborativeLink));
    }
}
