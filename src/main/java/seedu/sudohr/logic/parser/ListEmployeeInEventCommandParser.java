package seedu.sudohr.logic.parser;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.sudohr.commons.core.index.Index;
import seedu.sudohr.logic.commands.eventcommands.ListEmployeeInEventCommand;
import seedu.sudohr.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListEmployeeInEventCommand object
 */
public class ListEmployeeInEventCommandParser implements Parser<ListEmployeeInEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * ListEmployeeInEventCommandParser
     * and returns a ListEmployeeInEventCommandParser object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
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
