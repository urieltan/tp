package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.UNKNOWN_SHOW_TAG_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.address.logic.commands.ShowTagCommand;
import seedu.address.logic.commands.showtag.ShowTagContactCommand;
import seedu.address.logic.commands.showtag.ShowTagEventCommand;
import seedu.address.logic.commands.showtag.ShowTagTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContactTagMatchesKeywordPredicate;
import seedu.address.model.task.TaskTagMatchesKeywordPredicate;

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
        assert args != null;
        String[] splitArgs = args.trim().split(" ", 2);

        if (splitArgs[0].equals("contact")) {
            if (splitArgs.length < 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ShowTagContactCommand.MESSAGE_USAGE));
            }

            ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(" " + splitArgs[1], PREFIX_TAG);

            if (!arePrefixesPresent(argMultimap, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ShowTagContactCommand.MESSAGE_USAGE));
            }
            String[] keywords = argMultimap.getValue(PREFIX_TAG).get().trim().split("\\s+");

            if (keywords.length != 1 || keywords[0].trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ShowTagContactCommand.MESSAGE_USAGE));
            }
            return new ShowTagContactCommand(new ContactTagMatchesKeywordPredicate(keywords[0]));
        } else if (splitArgs[0].equals("todo")) {
            if (splitArgs.length < 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ShowTagTodoCommand.MESSAGE_USAGE));
            }

            ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(" " + splitArgs[1], PREFIX_TAG);

            if (!arePrefixesPresent(argMultimap, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ShowTagTodoCommand.MESSAGE_USAGE));
            }
            String[] keywords = argMultimap.getValue(PREFIX_TAG).get().trim().split("\\s+");

            if (keywords.length != 1 || keywords[0].trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ShowTagTodoCommand.MESSAGE_USAGE));
            }
            return new ShowTagTodoCommand(new TaskTagMatchesKeywordPredicate(keywords[0]));
        } else if (splitArgs[0].equals("event")) {
            if (splitArgs.length < 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ShowTagEventCommand.MESSAGE_USAGE));
            }

            ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(" " + splitArgs[1], PREFIX_TAG);

            if (!arePrefixesPresent(argMultimap, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ShowTagEventCommand.MESSAGE_USAGE));
            }
            String[] keywords = argMultimap.getValue(PREFIX_TAG).get().trim().split("\\s+");

            if (keywords.length != 1 || keywords[0].trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ShowTagEventCommand.MESSAGE_USAGE));
            }
            return new ShowTagEventCommand(new TaskTagMatchesKeywordPredicate(keywords[0]));
        } else {
            throw new ParseException(UNKNOWN_SHOW_TAG_COMMAND);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
