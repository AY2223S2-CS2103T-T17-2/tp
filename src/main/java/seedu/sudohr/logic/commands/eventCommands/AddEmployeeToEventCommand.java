package seedu.sudohr.logic.commands.eventCommands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.events.Event;
import seedu.sudohr.model.events.EventContainsEmployeePredicate;
import seedu.sudohr.model.person.Person;
import seedu.sudohr.commons.core.index.Index;

public class AddEmployeeToEventCommand extends Command {

    private final Index eventToAddIndex;
    private final Index personToAddIndex;

    public static final String COMMAND_WORD = "addEmployeeEvent";

    public static final String MESSAGE_DUPLICATE_PERSON = "This user already exists in the event in sudohr book";

    public static final String MESSAGE_SUCCESS = "New person %1$s is added to %2$s";

    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index is invalid";

    public static final String MESSAGE_INVALID_EVENT_DISPLAYED_INDEX = "The event index is invalid";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a employee to event in the sudohr book. ";

    public AddEmployeeToEventCommand(Index personIndex, Index eventIndex) {
        requireNonNull(personIndex);
        requireNonNull(eventIndex);
        eventToAddIndex = eventIndex;
        personToAddIndex = personIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        List<Event> lastShownEventList = model.getFilteredEventList();

        if (personToAddIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (eventToAddIndex.getZeroBased() >= lastShownEventList.size()) {
            throw new CommandException(MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Person personToAdd = lastShownPersonList.get(personToAddIndex.getZeroBased());

        Event eventToAdd = lastShownEventList.get(eventToAddIndex.getZeroBased());

        if (eventToAdd.hasPerson(personToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addEmployeeToEvent(eventToAdd, personToAdd);

        Set<Person> personsToList = eventToAdd.getAttendees();
        EventContainsEmployeePredicate predicate = new EventContainsEmployeePredicate(personsToList);

        model.updateFilteredPersonList(predicate);

        return new CommandResult(String.format(MESSAGE_SUCCESS, personToAdd, eventToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEmployeeToEventCommand // instanceof handles nulls
                        && eventToAddIndex.equals(((AddEmployeeToEventCommand) other).eventToAddIndex)
                        && personToAddIndex
                                .equals(((AddEmployeeToEventCommand) other).personToAddIndex));
    }

}
