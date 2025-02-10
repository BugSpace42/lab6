package lab5.utility;

import java.util.Map;

import lab5.managers.CommandManager;
import lab5.managers.ConsoleManager;

/**
 * Класс, который управляет работой программы.
 * @author Alina
 */
public class Runner {
    private CommandManager commandManager;
    private ConsoleManager consoleManager;
    private Map<String, Command> commands;

    /**
     * Перечисление кодов завершения выполнения команды.
     */
    public enum ExitCode {
        OK,
        EXIT,
        ERROR
    }

    private void launchCommand(String[] userCommand) {
        if (commands.containsKey(userCommand[0])) {
            commands.get(userCommand[0]).execute(userCommand);
        }
    }

    public Runner(CommandManager commandManager, ConsoleManager consoleManager) {
        this.commandManager = commandManager;
        this.consoleManager = consoleManager;
        this.commands = commandManager.getCommands();
        boolean running = true;
        consoleManager.greeting();

        while(running) {
            String[] currenrCommand = consoleManager.readCommand();
            launchCommand(currenrCommand);
        }
    }
}
