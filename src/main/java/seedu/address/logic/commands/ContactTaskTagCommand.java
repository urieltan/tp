package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.edit.EditTodoCommand.MESSAGE_DUPLICATE_PERSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.edit.EditContactCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Event;
import seedu.address.model.task.Task;
import seedu.address.model.task.Todo;

public class ContactTaskTagCommand extends Command {
    public static final String COMMAND_WORD = "contactTaskTag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a same tag for a specified Person and Task.\n"
            + "Parameters:" + PREFIX_TAG + "TAG "
            + PREFIX_CONTACT_INDEX + "CONTACT INDEX "
            + PREFIX_TASK_INDEX + "TASK INDEX\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TAG + "CS2103T "
            + PREFIX_CONTACT_INDEX + "1 "
            + PREFIX_TASK_INDEX + "2";
    public static final String MESSAGE_CONTACT_TASK_TAG_SUCCESS = "Created tag for \n Person: %1$s, \n Task: %2$s";

    private final Index contactIndex;
    private final Index taskIndex;
    private final EditPersonDescriptor editPersonDescriptor;
    private final EditTaskDescriptor editTaskDescriptor;

    public ContactTaskTagCommand(Index contactIndex, Index taskIndex,
                                 EditPersonDescriptor editPersonDescriptor, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(contactIndex);
        requireNonNull(taskIndex);
        this.contactIndex = contactIndex;
        this.taskIndex = taskIndex;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> contactList = model.getFilteredPersonList();
        List<Task> taskList = model.getFilteredTaskList();

        if (contactIndex.getZeroBased() >= contactList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        if (taskIndex.getZeroBased() >= taskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Person personToEdit = contactList.get(contactIndex.getZeroBased());
        Task taskToEdit = taskList.get(taskIndex.getZeroBased());

        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(String.format(MESSAGE_CONTACT_TASK_TAG_SUCCESS, editedPerson, editedTask), "CONTACT");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditContactCommand)) {
            return false;
        }

        // state check
        ContactTaskTagCommand e = (ContactTaskTagCommand) other;
        return contactIndex.equals(e.contactIndex)
                && editPersonDescriptor.equals(e.editPersonDescriptor)
                && taskIndex.equals(e.taskIndex)
                && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Set<Tag> updatedTags = mergeSet(editPersonDescriptor.getTags()
                .orElse(personToEdit.getTags()), personToEdit.getTags());

        return new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), updatedTags);
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Set<Tag> updatedTags = mergeSet(editTaskDescriptor.getTags()
                .orElse(taskToEdit.getTags()), taskToEdit.getTags());

        if (taskToEdit.isTodo()) {
            return new Todo(taskToEdit.getDescription(), taskToEdit.getDeadline(), taskToEdit.getRecurrence(),
                    ((Todo) taskToEdit).getCollaborativeLink(), updatedTags);
        } else if (taskToEdit.isEvent()) {
            return new Event(taskToEdit.getDescription(), taskToEdit.getStart(), taskToEdit.getEnd(),
                    taskToEdit.getRecurrence(), updatedTags);
        } else {
            assert false : "task is neither a todo or event!";
            return null;
        }
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Set<Tag> tags;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(tags);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns a tag set.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(tags) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getTags().equals(e.getTags());
        }
    }


    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditTaskDescriptor {
        private Set<Tag> tags;

        public EditTaskDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(tags);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns a tag set.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(tags) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskDescriptor e = (EditTaskDescriptor) other;

            return getTags().equals(e.getTags());
        }
    }

    // Function merging two sets using DoubleBrace Initialisation
    public static <T> Set<T> mergeSet(Set<T> a, Set<T> b) {
        if (a.equals(b)) {
            return a;
        } else {
            return new HashSet<T>() {
                {
                    addAll(a);
                    addAll(b);
                }
            };
        }
    }
}
