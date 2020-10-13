package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTodos.HOMEWORK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.add.AddTodoCommand;
import seedu.address.model.task.Todo;
import seedu.address.testutil.TodoBuilder;

public class AddTodoCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();
    private static final String HOMEWORK_DESC = "desc/homework ";
    private static final String HOMEWORK_DATE = "date/12-12-2020 ";
    private static final String HOMEWORK_TIME = "time/2359";
    private static final String INVALID_DATE = "date/1-1-2020";
    private static final String INVALID_TIME = "time/12000";

    @Test
    public void parse_allFieldsPresent_success() {
        Todo expectedTodo = new TodoBuilder(HOMEWORK).build();

        assertParseSuccess(parser, "todo " + HOMEWORK_DESC + HOMEWORK_DATE + HOMEWORK_TIME,
                new AddTodoCommand(expectedTodo));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE);

        // missing description
        assertParseFailure(parser, "todo " + HOMEWORK_DATE + HOMEWORK_TIME, expectedMessage);

        // missing date
        assertParseFailure(parser, "todo " + HOMEWORK_DESC + HOMEWORK_TIME, expectedMessage);

        // missing time
        assertParseFailure(parser, "todo " + HOMEWORK_DESC + HOMEWORK_DATE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE);

        // invalid date
        assertParseFailure(parser, "todo " + HOMEWORK_DESC + INVALID_DATE + HOMEWORK_TIME,
                expectedMessage);

        //Todo
        // invalid time (after implementing exceptions for wrong time format)
        //assertParseFailure(parser, "todo " + HOMEWORK_DESC + HOMEWORK_DATE + INVALID_TIME,
        //        expectedMessage);
    }

}
