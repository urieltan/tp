package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.due.DueByCommand;
import seedu.address.model.task.DueByPredicate;

public class DueByParserTest {
    private DueByCommandParser parser = new DueByCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DueByCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsDueByCommand() {
        DueByCommand expectedCommand = new DueByCommand(new DueByPredicate("12-12-2020 2359"));

        assertParseSuccess(parser, "date/12-12-2020 time/2359", expectedCommand);
    }
}
