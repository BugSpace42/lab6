package lab5.commands;

import lab5.managers.CollectionManager;
import lab5.managers.ConsoleManager;
import lab5.utility.Command;
import lab5.utility.Runner;

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
