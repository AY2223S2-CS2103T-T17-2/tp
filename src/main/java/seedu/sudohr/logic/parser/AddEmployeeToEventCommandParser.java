package seedu.sudohr.logic.parser;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EVENT_INDEX;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EMPLOYEE_INDEX;

import java.util.stream.Stream;

import seedu.sudohr.commons.core.index.Index;
import seedu.sudohr.logic.commands.eventCommands.AddEmployeeToEventCommand;
import seedu.sudohr.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddEmployeeToEventCommandParser implements Parser<AddEmployeeToEventCommand> {

    public AddEmployeeToEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EVENT_INDEX, PREFIX_EMPLOYEE_INDEX);

        if (!arePrefixesPresent(argMultimap,
                PREFIX_EVENT_INDEX,
                PREFIX_EMPLOYEE_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddEmployeeToEventCommand.MESSAGE_USAGE));
        }

        Index eventIndex = ParserUtil.parseEventIndex(argMultimap.getValue(PREFIX_EVENT_INDEX).get());
        Index employeeIndex = ParserUtil.parseEmployeeIndex(argMultimap.getValue(PREFIX_EMPLOYEE_INDEX).get());

        return new AddEmployeeToEventCommand(employeeIndex, eventIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
