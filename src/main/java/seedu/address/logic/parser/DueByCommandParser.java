package seedu.address.logic.parser;

import seedu.address.logic.commands.DueByCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.DueByPredicate;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class DueByCommandParser implements Parser<DueByCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DueByCommand
     * and returns a DueByCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DueByCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.trim().split(" ", 2);
            String date = splitArgs[0].split("/")[1];
            // if date not in DD-MM-YYYY format
            if (date.length() < 10) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DueByCommand.MESSAGE_USAGE));
            }

            String time = splitArgs[1].split("/")[1];
            // if time not in HHmm format
            if (time.length() < 4) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DueByCommand.MESSAGE_USAGE));
            }

            String deadline = date + " " + time;
            return new DueByCommand(new DueByPredicate(deadline));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DueByCommand.MESSAGE_USAGE));
        }
    }
}
