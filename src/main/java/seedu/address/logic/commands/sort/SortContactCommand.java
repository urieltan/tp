package seedu.address.logic.commands.sort;

import java.util.Comparator;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonNameComparator;

public class SortContactCommand extends SortCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts contacts by name ";

    public static final String MESSAGE_SUCCESS = "Sorted contacts by name";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        Comparator<Person> comparator = new PersonNameComparator();
        model.updateSortedPersonList(comparator);
        return new CommandResult(MESSAGE_SUCCESS, "CONTACT");
    }
}
