package seedu.sudohr.logic.commands.eventCommands;

import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.model.Model;

public class DeleteEmployeeFromEventCommand extends Command{
    public static final String COMMAND_WORD = "deleteEmployeeEvent";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Delete employee from Event Command", false, false);
    }
    
}
