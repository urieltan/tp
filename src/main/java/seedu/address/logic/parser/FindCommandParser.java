package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.UNKNOWN_FIND_COMMAND;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.find.FindContactCommand;
import seedu.address.logic.commands.find.FindEventCommand;
import seedu.address.logic.commands.find.FindTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String[] splitArgs = args.trim().split(" ", 2);
        if (splitArgs[0].equals("contact")) {
            if (splitArgs.length < 2) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
            }
            String trimmedArgs = splitArgs[1].trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
            }

            String[] nameKeywords = trimmedArgs.split("\\s+");

            return new FindContactCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (splitArgs[0].equals("todo")) {
            String trimmedArgs = splitArgs[1].trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
            }

            String[] nameKeywords = trimmedArgs.split("\\s+");

            return new FindTodoCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (splitArgs[0].equals("event")){
            String trimmedArgs = splitArgs[1].trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
            }

            String[] nameKeywords = trimmedArgs.split("\\s+");

            return new FindEventCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else {
            throw new ParseException(UNKNOWN_FIND_COMMAND);
        }
    }

}
