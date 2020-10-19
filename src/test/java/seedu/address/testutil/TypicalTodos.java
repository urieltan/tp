package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.task.Todo;

/**
 * A utility class containing a list of {@code To-dos} objects to be used in tests.
 */
public class TypicalTodos {
    public static final Todo HOMEWORK = new TodoBuilder().withDescription("homework")
            .withDateTime("12-12-2020 2359").build();
    public static final Todo CHORES = new TodoBuilder().withDescription("do chores")
            .withDateTime("01-01-2020 1800").withRecurrence("1 week").build();

    private TypicalTodos() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical todos.
     */
    public static AddressBook getTypicalTodosAddressBook() {
        AddressBook ab = new AddressBook();
        for (Todo todo: getTypicalTodos()) {
            ab.addTodo(todo);
        }
        return ab;
    }

    public static List<Todo> getTypicalTodos() {
        return new ArrayList<>(Arrays.asList(HOMEWORK, CHORES));
    }
}
