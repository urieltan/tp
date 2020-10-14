package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATE;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Event;

/**
 * Adds a event to the address book.
 */
public class AddEventCommand extends AddCommand {


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a event to the TaskList. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_STARTDATE + "START DATE "
            + PREFIX_STARTDATE + "START TIME "
            + PREFIX_ENDDATE + "END DATE "
            + PREFIX_ENDTIME + "END TIME";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Event toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        model.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getDescriptionDateTime()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventCommand // instanceof handles nulls
                && toAdd.equals(((AddEventCommand) other).toAdd));
    }
}
