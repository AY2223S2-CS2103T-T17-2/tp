package seedu.sudohr.logic.commands.eventCommands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.sudohr.commons.core.index.Index;
import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.events.Event;
import seedu.sudohr.model.events.EventContainsEmployeePredicate;
import seedu.sudohr.model.person.Person;

public class DeleteEmployeeFromEventCommand extends Command {
    public static final String COMMAND_WORD = "deleteEmployeeEvent";

    public static final String MESSAGE_PERSON_DOES_NOT_EXIST = "This user does not exists in the event in sudohr book";

    public static final String MESSAGE_SUCCESS = "person %1$s is deleted from %2$s";

    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index is invalid";

    public static final String MESSAGE_INVALID_EVENT_DISPLAYED_INDEX = "The event index is invalid";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a employee from event in the sudohr book. ";

    private final Index eventToDeleteIndex;
    private final Index personToDeleteIndex;

    public DeleteEmployeeFromEventCommand(Index personIndex, Index eventIndex) {
        requireNonNull(personIndex);
        requireNonNull(eventIndex);
        eventToDeleteIndex = eventIndex;
        personToDeleteIndex = personIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        List<Event> lastShownEventList = model.getFilteredEventList();

        if (personToDeleteIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (eventToDeleteIndex.getZeroBased() >= lastShownEventList.size()) {
            throw new CommandException(MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownPersonList.get(personToDeleteIndex.getZeroBased());

        Event eventToDelete = lastShownEventList.get(eventToDeleteIndex.getZeroBased());

        if (!eventToDelete.hasPerson(personToDelete)) {
            throw new CommandException(MESSAGE_PERSON_DOES_NOT_EXIST);
        }

        model.deleteEmployeeFromEvent(eventToDelete, personToDelete);

        Set<Person> personsToList = eventToDelete.getAttendees();
        EventContainsEmployeePredicate predicate = new EventContainsEmployeePredicate(personsToList);

        model.updateFilteredPersonList(predicate);

        return new CommandResult(String.format(MESSAGE_SUCCESS, personToDelete, eventToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteEmployeeFromEventCommand // instanceof handles nulls
                        && eventToDeleteIndex.equals(((DeleteEmployeeFromEventCommand) other).eventToDeleteIndex)
                        && personToDeleteIndex
                                .equals(((DeleteEmployeeFromEventCommand) other).personToDeleteIndex));
    }

}
