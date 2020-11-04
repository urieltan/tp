package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.find.FindContactCommand;
import seedu.address.model.person.ContactMatchesFindKeywordPredicate;

public class FindContactCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "contact     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));

        // no tag keyword given
        assertParseFailure(parser, "contact " + PREFIX_TAG,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));

        // more than one tag keyword given
        assertParseFailure(parser, "contact " + PREFIX_TAG + "tag1 tag2",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));

        // no name keyword given
        assertParseFailure(parser, "contact " + PREFIX_NAME,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        FindContactCommand expectedFindContactCommandWithName =
            new FindContactCommand(new ContactMatchesFindKeywordPredicate(Arrays.asList("Alice", "Bob")));
        FindContactCommand expectedFindContactCommandWithTag =
            new FindContactCommand(new ContactMatchesFindKeywordPredicate("friends"));
        FindContactCommand expectedFindContactCommand =
            new FindContactCommand(new ContactMatchesFindKeywordPredicate(Arrays.asList("Alice", "Bob"), "friends"));

        // no leading and trailing whitespaces name keyword
        assertParseSuccess(parser, "contact " + PREFIX_NAME + "Alice Bob", expectedFindContactCommandWithName);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "contact "
            + PREFIX_NAME + "\n Alice \n \t Bob  \t", expectedFindContactCommandWithName);

        // tag keyword given
        assertParseSuccess(parser, "contact " + PREFIX_TAG + "friends", expectedFindContactCommandWithTag);

        // both name and tag keyword given
        assertParseSuccess(parser, "contact "
            + PREFIX_NAME + "Alice Bob " + PREFIX_TAG + "friends", expectedFindContactCommand);
    }
}
