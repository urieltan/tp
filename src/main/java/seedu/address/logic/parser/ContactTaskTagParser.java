package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ContactTaskTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.commands.ContactTaskTagCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ContactTaskTagCommand.EditTaskDescriptor;
import seedu.address.model.tag.Tag;

public class ContactTaskTagParser implements Parser<ContactTaskTagCommand> {
    public ContactTaskTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] splitArgs = args.trim().split(" ", 1);
        if (splitArgs.length < 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ContactTaskTagCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(" " + splitArgs[0], PREFIX_DESCRIPTION,
                PREFIX_CONTACT_INDEX, PREFIX_TASK_INDEX);

        Index contactIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CONTACT_INDEX).get());
        Index taskIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TASK_INDEX).get());

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_DESCRIPTION)).ifPresent(editPersonDescriptor::setTags);

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_DESCRIPTION)).ifPresent(editTaskDescriptor::setTags);

        return new ContactTaskTagCommand(contactIndex, taskIndex, editPersonDescriptor, editTaskDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
