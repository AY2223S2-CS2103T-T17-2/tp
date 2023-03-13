package seedu.sudohr.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EVENT_TITLE;

import seedu.sudohr.commons.core.index.Index;
import seedu.sudohr.logic.commands.eventcommands.UpdateEventCommand;
import seedu.sudohr.logic.commands.eventcommands.UpdateEventCommand.EditEventDescriptor;
import seedu.sudohr.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UpdateEventCommand object
 */
public class UpdateEventCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateEventCommand
     * and returns an UpdateEventCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EVENT_TITLE);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdateEventCommand.MESSAGE_USAGE), pe);
        }

        EditEventDescriptor editEventDescriptor = new EditEventDescriptor();
        if (argMultimap.getValue(PREFIX_EVENT_TITLE).isPresent()) {
            editEventDescriptor.setTitle(ParserUtil.parseEventTitle(argMultimap.getValue(PREFIX_EVENT_TITLE).get()));
        }

        if (!editEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(UpdateEventCommand.MESSAGE_NOT_EDITED);
        }

        return new UpdateEventCommand(index, editEventDescriptor);
    }
}
