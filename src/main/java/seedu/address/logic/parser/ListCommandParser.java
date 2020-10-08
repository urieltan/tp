package seedu.address.logic.parser;

import java.util.stream.Stream;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.list.ListAllCommand;
import seedu.address.logic.commands.list.ListContactCommand;
import seedu.address.logic.commands.list.ListEventCommand;
import seedu.address.logic.commands.list.ListTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        if (args.trim().equals("contact")) {
            return new ListContactCommand();
        } else if (args.trim().equals("todo")) {
            return new ListTodoCommand();
        } else if (args.trim().equals("event")) {
            return new ListEventCommand();
        } else if (args.trim().equals("all")) {
            return new ListAllCommand();
        }
        else{
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
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
