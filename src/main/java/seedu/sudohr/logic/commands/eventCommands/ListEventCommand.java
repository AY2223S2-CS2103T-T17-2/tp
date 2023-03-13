package seedu.sudohr.logic.commands.eventcommands;

import javafx.collections.ObservableList;
import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.events.Event;

/**
 * Lists all events in the sudohr book to the user.
 */
public class ListEventCommand extends Command {
    public static final String COMMAND_WORD = "listEvent";

    @Override
    public CommandResult execute(Model model) {
        final StringBuilder builder = new StringBuilder();
        ObservableList<Event> eventList = model.getEventList();

        builder.append("listed events\n");
        for (int i = 0; i < eventList.size(); i++) {
            builder.append(eventList.get(i).toString());
        }

        return new CommandResult(builder.toString(), false, false);
    }
}
