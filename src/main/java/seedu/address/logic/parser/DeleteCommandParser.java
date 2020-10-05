package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.delete.DeleteContactCommand;
import seedu.address.logic.commands.delete.DeleteEventCommand;
import seedu.address.logic.commands.delete.DeleteTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        String[] splitArgs = args.trim().split(" ", 2);
        try {
            Index index = ParserUtil.parseIndex(splitArgs[1]);
            if (splitArgs[0].trim().equals("contact")) {
                return new DeleteContactCommand(index);
            } else if (splitArgs[0].trim().equals("todo")) {
                return new DeleteTodoCommand(index);
            } else {
                return new DeleteEventCommand(index);
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteContactCommand.MESSAGE_USAGE), pe);
        }
    }

}
