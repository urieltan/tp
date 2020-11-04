package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.getTypicalEventsTaskList;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.link.LinkMeetingCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.task.Event;
import seedu.address.model.task.MeetingLink;
import seedu.address.model.task.Task;
import seedu.address.model.task.Todo;


public class LinkMeetingCommandTest {
    private Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalEventsTaskList());

    @Test
    public void constructor_nullMeetingLink_throwsNullPointerException() {
        Index index = Index.fromOneBased(1);
        assertThrows(NullPointerException.class, () -> new LinkMeetingCommand(index, null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        ModelStubAcceptingLinkAdded modelStub = new ModelStubAcceptingLinkAdded();
        Index index = Index.fromOneBased(100);
        MeetingLink link = new MeetingLink("Google Meet",
                "https://www.google.com", "20-01-2020 2359");
        try {
            CommandResult commandResult = new LinkMeetingCommand(index, link).execute(modelStub);
        } catch (CommandException e) {
            assertEquals(new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX).toString(),
                    e.toString());
        }
    }

    @Test
    public void equals() {
        Index index = Index.fromOneBased(1);
        MeetingLink googleMeet = new MeetingLink("Google Meet",
                "https://www.google.com", "20-01-2020 2359");
        MeetingLink zoomLink = new MeetingLink("Zoom Link",
                "https://www.zoom.com", "20-01-2020 2359");
        LinkMeetingCommand linkGoogle = new LinkMeetingCommand(index, googleMeet);
        LinkMeetingCommand linkZoom = new LinkMeetingCommand(index, zoomLink);

        // same object -> returns true
        assertTrue(linkGoogle.equals(linkGoogle));

        // same values -> returns true
        LinkMeetingCommand linkZoomCopy = new LinkMeetingCommand(index, zoomLink);
        assertTrue(linkZoom.equals(linkZoomCopy));

        // different types -> returns false
        assertFalse(linkGoogle.equals(1));

        // null -> returns false
        assertFalse(linkGoogle.equals(null));

        // different person -> returns false
        assertFalse(linkGoogle.equals(linkZoom));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        // Needs to be null to update "Due Soon" section in GUI after adding a task.
        @Override
        public ObservableList<Task> getDueSoonTaskList() {
            return null;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<? super Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTodo(Todo todo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTodo(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskList getTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markAsDone(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedTaskList(Comparator<Task> taskComparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedPersonList(Comparator<Person> personComparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean filteredTaskListIsEmpty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean filteredAddressBookIsEmpty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the to-do being added.
     */
    private class ModelStubAcceptingLinkAdded extends ModelStub {
        final ArrayList<Todo> todosAdded = new ArrayList<>();

        @Override
        public boolean hasTask(Task todo) {
            requireNonNull(todo);
            return todosAdded.stream().anyMatch(todo::isSameTask);
        }

        @Override
        public void addTodo(Todo todo) {
            requireNonNull(todo);
            todosAdded.add(todo);
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            return expectedModel.getFilteredTaskList();
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

    }
}
