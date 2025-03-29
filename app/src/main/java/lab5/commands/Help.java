package lab5.commands;

import lab5.managers.ConsoleManager;
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
        super("help", "вывести справку по доступным командам", 0);
        this.runner = runner;
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        try {
            ConsoleManager.println("Доступные команды:");
            for (Command command : runner.commandManager.getCommands().values()) {
                ConsoleManager.println(command.getName() + ": " + command.getDescription());
            }
            return Runner.ExitCode.OK;
        } catch (Exception e) {
            ConsoleManager.printError("Непредвиденная ошибка!");
            return ExitCode.ERROR;
        }
    }
}
