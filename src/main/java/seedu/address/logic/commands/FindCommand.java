package seedu.address.logic.commands;

/**
 * Represent a find command with hidden internal logic and the ability to be executed.
 */

public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "employee/project/skill"
        + ": Finds all employees/project/skill which contain "
        + "the specified arguments (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: ARGUMENTS \n"
        + FindEmployeeCommand.MESSAGE_USAGE + "\n"
        + FindProjectCommand.MESSAGE_USAGE + "\n"
        + FindSkillCommand.MESSAGE_USAGE + "\n";
}
