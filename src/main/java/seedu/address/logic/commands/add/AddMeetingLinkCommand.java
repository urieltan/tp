package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Add a meeting link to a event.
 */
public class AddMeetingLinkCommand extends AddCommand {

    public static final String COMMAND_WORD = "remark";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from remark");
    }
}