package seedu.sudohr.logic.commands.eventCommands;

import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.model.Model;

public class UpdateEventCommand extends Command {

    public static final String COMMAND_WORD = "updateEvent";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Update event command", false, false);
    }
}
