package lab6.commands;

import lab6.managers.CollectionManager;
import lab6.managers.ConsoleManager;
import lab6.utility.Command;
import lab6.utility.Runner;

/**
 * Выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 * @author Alina
 */
public class Show extends Command {
    public Show(){
        super("show", "вывести все элементы коллекции в строковом представлении", 0);
    }

    /**
     * Выполняет команду.
     */
    @Override
    public Runner.ExitCode execute(String[] args) {
        CollectionManager collectionManager = CollectionManager.getCollectionManager();
        ConsoleManager.println(collectionManager.toString());
        return Runner.ExitCode.OK;
    }
}
