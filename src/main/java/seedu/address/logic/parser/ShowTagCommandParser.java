package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.address.logic.commands.ShowTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TagMatchesKeywordPredicate;

/**
 * Parses input arguments and creates a new ShowTagCommand object
 */
public class ShowTagCommandParser implements Parser<ShowTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ShowTagCommand
     * and returns a ShowTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(" " + args, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TAG)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ShowTagCommand.MESSAGE_USAGE));
        }
        String[] keywords = argMultimap.getValue(PREFIX_TAG).get().trim().split("\\s+");

        if (keywords.length != 1 || keywords[0].trim().equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ShowTagCommand.MESSAGE_USAGE));
        }

        return new ShowTagCommand(new TagMatchesKeywordPredicate(keywords[0]));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
