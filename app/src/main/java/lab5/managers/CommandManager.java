package lab5.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import lab5.commands.Command;

/**
 * Класс, который управляет командами.
 * @author Alina
 */
public class CommandManager {
    private HashMap<String, Command> commands = new HashMap<>();
    private HashMap<String, String> descriptions = new HashMap<>();
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
     * Добавляет новую команду с описанием.
     * @param name название команды
     * @param command команда
     * @param description описание команды
     */
    public void newCommand(String name, Command command, String description) {
        commands.put(name, command);
        descriptions.put(name, description);
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

    /**
     * @return список названий доступных команд
     */
    public ArrayList<String> getCommandList() {
        Set<String> commandSet = commands.keySet();
        ArrayList<String> commandList = new ArrayList<String>();
        for (String cmd : commandSet) {
            commandList.add(cmd);
        }
        return commandList;
    }
}
