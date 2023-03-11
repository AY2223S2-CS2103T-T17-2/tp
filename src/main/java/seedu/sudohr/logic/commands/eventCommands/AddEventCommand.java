package seedu.sudohr.logic.commands.eventCommands;

import static java.util.Objects.requireNonNull;

import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.events.Event;

public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "addEvent";

    private final Event eventToAdd;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a event to the sudohr book. ";
    public static final String MESSAGE_SUCCESS = "New event added: %1$s";

    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the sudohr book";

    public AddEventCommand(Event event) {
        requireNonNull(event);
        eventToAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(eventToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.addEvent(eventToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, eventToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventCommand // instanceof handles nulls
                        && eventToAdd.equals(((AddEventCommand) other).eventToAdd));
    }
}
