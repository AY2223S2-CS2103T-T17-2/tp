package seedu.sudohr.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EVENT_TITLE;

import seedu.sudohr.commons.core.index.Index;
import seedu.sudohr.logic.commands.eventCommands.UpdateEventCommand;
import seedu.sudohr.logic.commands.eventCommands.UpdateEventCommand.EditEventDescriptor;
import seedu.sudohr.logic.parser.exceptions.ParseException;

public class UpdateEventCommandParser {

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
