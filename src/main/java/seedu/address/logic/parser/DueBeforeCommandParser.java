package seedu.address.logic.parser;

import seedu.address.logic.commands.DueBeforeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.DueBeforePredicate;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class DueBeforeCommandParser implements Parser<DueBeforeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DueBeforeCommand
     * and returns a DueBeforeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DueBeforeCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.trim().split(" ", 2);
            String date = splitArgs[0].split("/")[1];
            String time = splitArgs[1].split("/")[1];
            String deadline = date + " " + time;
            return new DueBeforeCommand(new DueBeforePredicate(deadline));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DueBeforeCommand.MESSAGE_USAGE));
        }
    }

}
