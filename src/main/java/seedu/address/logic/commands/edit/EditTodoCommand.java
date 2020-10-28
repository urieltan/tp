package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.sql.Struct;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.Todo;

/**
 * Edits the details of an existing todo in the Lifebook.
 */
public class EditTodoCommand extends EditCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the todo identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TIME + "TIME] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "A new description "
            + PREFIX_DATE + "20-01-2020 "
            + PREFIX_TIME + "2350";

    public static final String MESSAGE_EDIT_TODO_SUCCESS = "Edited Todo: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This todo already exists in the Lifebook.";

    private final Index index;
    private final EditTodoCommand.EditTodoDescriptor editTodoDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editTodoDescriptor details to edit the todo with
     */
    public EditTodoCommand(Index index, EditTodoCommand.EditTodoDescriptor editTodoDescriptor) {
        requireNonNull(index);
        requireNonNull(editTodoDescriptor);

        this.index = index;
        this.editTodoDescriptor = new EditTodoCommand.EditTodoDescriptor(editTodoDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Todo todoToEdit = (Todo) lastShownList.get(index.getZeroBased());
        Todo editedTodo = createEditedTodo(todoToEdit, editTodoDescriptor);

        if (!todoToEdit.isSameTodo(editedTodo) && model.hasTask(editedTodo)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setTask(todoToEdit, editedTodo);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TODO_SUCCESS, editedTodo), "TASK");
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Todo createEditedTodo(Todo todoToEdit,
                                             EditTodoCommand.EditTodoDescriptor editTodoDescriptor) {
        assert todoToEdit != null;

        String description = editTodoDescriptor.getDescription().orElse(todoToEdit.getDescription());
        String previousDateTime = todoToEdit.getInputDate();
        String date = editTodoDescriptor.getDate().orElse(previousDateTime.split(" ")[0]);
        String time = editTodoDescriptor.getTime().orElse(previousDateTime.split(" ")[1]);

        return new Todo(description, date + " " + time);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTodoCommand)) {
            return false;
        }

        // state check
        EditTodoCommand e = (EditTodoCommand) other;
        return index.equals(e.index)
                && editTodoDescriptor.equals(e.editTodoDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditTodoDescriptor {
        private String description;
        private String date;
        private String time;

        public EditTodoDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTodoDescriptor(EditTodoCommand.EditTodoDescriptor toCopy) {
            setDescription(toCopy.description);
            setDate(toCopy.date);
            setTime(toCopy.time);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, date, time);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Optional<String> getDate() {
            return Optional.ofNullable(date);
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Optional<String> getTime() {
            return Optional.ofNullable(time);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTodoCommand.EditTodoDescriptor)) {
                return false;
            }

            // state check
            EditTodoCommand.EditTodoDescriptor e = (EditTodoCommand.EditTodoDescriptor) other;

            return getDescription().equals(e.getDescription())
                    && getDate().equals(e.getDate())
                    && getTime().equals(e.getTime());
        }
    }
}
