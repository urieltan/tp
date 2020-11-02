package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.due.DueByCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.DueByPredicate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses input arguments and creates a new DueByCommand object
 */
public class DueByCommandParser implements Parser<DueByCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DueByCommand
     * and returns a DueByCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DueByCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] splitArgs = args.trim().split(" ", 1);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(" " + splitArgs[0], PREFIX_DATE,
            PREFIX_TIME);

        String date = argMultimap.getValue(PREFIX_DATE).orElseThrow(() -> new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DueByCommand.MESSAGE_USAGE)));
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate checkDate = LocalDate.parse(date, dateFormat);
        } catch (DateTimeParseException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_DATE_FORMAT);
        }

        String time = argMultimap.getValue(PREFIX_TIME).orElseThrow(() -> new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DueByCommand.MESSAGE_USAGE)));
        try {
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
            LocalTime checkTime = LocalTime.parse(time, timeFormat);
        } catch (DateTimeParseException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_TIME_FORMAT);
        }

        String deadline = date + " " + time;
        return new DueByCommand(new DueByPredicate(deadline));
    }
}
