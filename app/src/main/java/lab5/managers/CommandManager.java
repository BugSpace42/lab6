package lab5.managers;

import java.util.ArrayList;
import java.util.HashMap;

import lab5.commands.Command;

/**
 * Класс, который управляет командами.
 * @author Alina
 */
public class CommandManager {
    private HashMap<String, Command> commands = new HashMap<>();
    private ArrayList<String> commandHistory = new ArrayList<>();

    /**
     * Добавляет новую команду.
     * @param name название команды
     * @param command команда
     */
    public void newCommand(String name, Command command) {
        commands.put(name, command);
    }

    /**
     * Добавляет команду в историю.
     */
    public void addToHistory(String command) {
        commandHistory.add(command);
    }

    /**
     * @return словарь с доступными командами
     */
    public HashMap<String, Command> getCommands() {
        return commands;
    }

    /**
     * @return список выполненных команд 
     */
    public ArrayList<String> getCommandHistory() {
        return commandHistory;
    }
}
