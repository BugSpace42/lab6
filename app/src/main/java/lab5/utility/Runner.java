package lab5.utility;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

import lab5.exceptions.UnknownCommandException;
import lab5.managers.CollectionManager;
import lab5.managers.CommandManager;
import lab5.managers.ConsoleManager;
import lab5.managers.FileManager;

/**
 * Класс, который управляет работой программы.
 * @author Alina
 */
public class Runner {
    public CommandManager commandManager;
    public ConsoleManager consoleManager;
    public CollectionManager collectionManager;
    public FileManager fileManager;
    public Map<String, Command> commands;
    private boolean running = false;
    public HashSet<String> scripts = new HashSet<>();;

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
    public void launchCommand(String[] userCommand) {
        try {
            if (!commands.containsKey(userCommand[0])){
                throw new UnknownCommandException("Не найдена команда " + userCommand[0]);
            }

            ExitCode exitCode = commands.get(userCommand[0]).execute(userCommand);
            switch (exitCode) {
                case OK -> {
                    consoleManager.println("Команда " + userCommand[0] + " выполнена успешно.");
                    commandManager.addToHistory(userCommand[0]);
                }
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
        } catch (UnknownCommandException e) {
            consoleManager.printError(e.getMessage());
            consoleManager.println("Для получения списка команд введите \"help\".");
        }
    }

    public Runner(CommandManager commandManager, ConsoleManager consoleManager, FileManager fileManager) {
        this.commandManager = commandManager;
        this.consoleManager = consoleManager;
        this.fileManager = fileManager;
        this.commands = commandManager.getCommands();
    }

    /**
     * Управляет работой программы.
     */
    public void run() {
        running = true;
        if (!fileManager.isFileExist(fileManager.getCollectionFileName())) {
            consoleManager.printError("Не найден файл " + fileManager.getCollectionFileName());
            consoleManager.println("Создана пустая коллекция.");
        }
        else {
            try {
                collectionManager = new CollectionManager(fileManager.readCollection());
            } catch (IOException e) {
                consoleManager.printError("Невозможно прочитать коллекцию из файла " + fileManager.getCollectionFileName());
                consoleManager.println("Создана пустая коллекция.");
            }
        }
        consoleManager.greeting();

        while(running) {
            String[] currenrCommand = consoleManager.readCommand();
            launchCommand(currenrCommand);
        }
    }

    public boolean getRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
