package lab5.commands;

import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 * Выводит справку по доступным командам.
 * @author Alina
 */
public class Help extends Command{
    private final Runner runner;

    public Help(Runner runner) {
        super("help", "вывести справку по доступным командам");
        this.runner = runner;
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        if (args.length > 1) {
            runner.consoleManager.printError("Введено слишком много аргументов.");
            return ExitCode.ERROR;
        }
        for (Command command : runner.commandManager.getCommands().values()) {
            runner.consoleManager.println(command.getName() + ": " + command.getDescription());
        }
        return ExitCode.OK;
    }
}
