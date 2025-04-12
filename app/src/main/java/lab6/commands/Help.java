package lab6.commands;

import lab6.managers.ConsoleManager;
import lab6.utility.Command;
import lab6.utility.Runner;
import lab6.utility.Runner.ExitCode;

/**
 * Выводит справку по доступным командам.
 * @author Alina
 */
public class Help extends Command{

    public Help() {
        super("help", "вывести справку по доступным командам", 0);
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        Runner runner = Runner.getRunner();
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
