package seedu.sudohr.logic.parser;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.sudohr.logic.commands.AddCommand;
import seedu.sudohr.logic.commands.ClearCommand;
import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.DeleteCommand;
import seedu.sudohr.logic.commands.EditCommand;
import seedu.sudohr.logic.commands.ExitCommand;
import seedu.sudohr.logic.commands.FindCommand;
import seedu.sudohr.logic.commands.HelpCommand;
import seedu.sudohr.logic.commands.ListCommand;
import seedu.sudohr.logic.commands.eventCommands.AddEmployeeToEventCommand;
import seedu.sudohr.logic.commands.eventCommands.AddEventCommand;
import seedu.sudohr.logic.commands.eventCommands.DeleteEmployeeFromEventCommand;
import seedu.sudohr.logic.commands.eventCommands.DeleteEventCommand;
import seedu.sudohr.logic.commands.eventCommands.ListEmployeeInEventCommand;
import seedu.sudohr.logic.commands.eventCommands.ListEventCommand;
import seedu.sudohr.logic.commands.eventCommands.UpdateEventCommand;
import seedu.sudohr.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class SudoHrParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

            case AddCommand.COMMAND_WORD:
                return new AddCommandParser().parse(arguments);

            case EditCommand.COMMAND_WORD:
                return new EditCommandParser().parse(arguments);

            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommandParser().parse(arguments);

            case ClearCommand.COMMAND_WORD:
                return new ClearCommand();

            case FindCommand.COMMAND_WORD:
                return new FindCommandParser().parse(arguments);

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            case AddEventCommand.COMMAND_WORD:
                return new AddEventCommandParser().parse(arguments);

            case AddEmployeeToEventCommand.COMMAND_WORD:
                return new AddEmployeeToEventCommandParser().parse(arguments);

            case DeleteEventCommand.COMMAND_WORD:
                return new DeleteEventCommandParser().parse(arguments);

            case DeleteEmployeeFromEventCommand.COMMAND_WORD:
                return new DeleteEmployeeFromEventCommandParser().parse(arguments);

            case ListEventCommand.COMMAND_WORD:
                return new ListEventCommand();

            case ListEmployeeInEventCommand.COMMAND_WORD:
                return new ListEmployeeInEventCommandParser().parse(arguments);

            case UpdateEventCommand.COMMAND_WORD:
                return new UpdateEventCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
