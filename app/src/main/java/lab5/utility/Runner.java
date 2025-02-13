package lab5.utility;

import java.util.Map;

import lab5.managers.CollectionManager;
import lab5.managers.CommandManager;
import lab5.managers.ConsoleManager;

/**
 * Класс, который управляет работой программы.
 * @author Alina
 */
public class Runner {
    public CommandManager commandManager;
    public ConsoleManager consoleManager;
    public CollectionManager collectionManager;
    public Map<String, Command> commands;
    private boolean running = false;

    /**
     * Перечисление кодов завершения выполнения команды.
     */
    public enum ExitCode {
        OK,
        EXIT,
        ERROR
    }

    /**
     * Запускает команду, которую ввёл пользователь.
     * @param userCommand команда
     */
    private void launchCommand(String[] userCommand) {
        if (commands.containsKey(userCommand[0])) {
            ExitCode exitCode = commands.get(userCommand[0]).execute(userCommand);

            switch (exitCode) {
                case OK -> consoleManager.println("Команда " + userCommand[0] + " выполнена успешно.");
                case ERROR -> {
                    consoleManager.println("При выполнении команды " + userCommand[0] + " произошла ошибка.");
                    consoleManager.println("Команда " + userCommand[0] + " не была выполнена.");
                }
                case EXIT -> {
                    consoleManager.println("Получена команда выхода из программы.");
                    consoleManager.println("Завершение работы программы.");
                    running = false;
                }
            }
        }
    }

    public Runner(CommandManager commandManager, ConsoleManager consoleManager, CollectionManager collectionManager) {
        this.commandManager = commandManager;
        this.consoleManager = consoleManager;
        this.collectionManager = collectionManager;
        this.commands = commandManager.getCommands();
    }

    /**
     * Управляет работой программы.
     */
    public void run() {
        running = true;
        consoleManager.greeting();

        while(running) {
            String[] currenrCommand = consoleManager.readCommand();
            launchCommand(currenrCommand);
        }
    }
}
