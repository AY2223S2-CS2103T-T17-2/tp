package seedu.sudohr.logic.parser;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;


import seedu.sudohr.commons.core.index.Index;
import seedu.sudohr.logic.commands.eventCommands.ListEmployeeInEventCommand;
import seedu.sudohr.logic.parser.exceptions.ParseException;


public class ListEmployeeInEventCommandParser implements Parser<ListEmployeeInEventCommand> {

    public ListEmployeeInEventCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ListEmployeeInEventCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListEmployeeInEventCommand.MESSAGE_USAGE), pe);
        }
    }

}