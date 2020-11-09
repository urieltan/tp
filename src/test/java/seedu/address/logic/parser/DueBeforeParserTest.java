package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.due.DueBeforeCommand;
import seedu.address.model.task.DueBeforePredicate;

public class DueBeforeParserTest {
    private DueBeforeCommandParser parser = new DueBeforeCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DueBeforeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "date/12-12-20 time/2359",
            String.format(Messages.MESSAGE_INVALID_DATE_FORMAT));

        assertParseFailure(parser, "date/12-12-2020",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DueBeforeCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "date/12-12-2020 time/5000",
            String.format(Messages.MESSAGE_INVALID_TIME_FORMAT));
    }

    @Test
    public void parse_validArgs_returnsDueBeforeCommand() {
        DueBeforeCommand expectedCommand = new DueBeforeCommand(new DueBeforePredicate("12-12-2020 2359"));

        assertParseSuccess(parser, "date/12-12-2020 time/2359", expectedCommand);
    }
}
