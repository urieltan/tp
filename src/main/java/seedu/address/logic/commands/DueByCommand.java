package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.DueByPredicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

public class DueByCommand extends Command {
    public static final String COMMAND_WORD = "itemsDueBy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows Todos/Events due by a certain date and time.\n"
            + "Parameters: "
            + PREFIX_DATE + "DD-MM-YYYY "
            + PREFIX_TIME + "HHmm \n"
            + "EXAMPLE: " + COMMAND_WORD + " "
            + PREFIX_DATE + "12-12-2020 "
            + PREFIX_TIME + "2359";

    public static final String MESSAGE_SUCCESS = "Here are the list of Todos/Events due by: ";

    private DueByPredicate predicate;

    public DueByCommand(DueByPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
