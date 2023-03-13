package seedu.sudohr.logic.commands.eventcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.commons.core.index.Index;
import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.events.Event;
import seedu.sudohr.model.events.EventContainsEmployeePredicate;
import seedu.sudohr.model.person.Person;

/**
 * Lists all employees attending a event identified using it's displayed index
 * in sudohr book.
 */

public class ListEmployeeInEventCommand extends Command {
    public static final String COMMAND_WORD = "listEventEmployee";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the employees part of the event identified by the index number "
            + "used in the displayed employees list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    /**
     * Creates an ListEmployeeInEventCommand to list all employees attending the
     * event at the specified {@code eventIndex}
     */
    public ListEmployeeInEventCommand(Index index) {
        requireNonNull(index);
        targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Set<Person> personsToList = lastShownList.get(targetIndex.getZeroBased()).getAttendees();
        EventContainsEmployeePredicate predicate = new EventContainsEmployeePredicate(personsToList);

        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
}
