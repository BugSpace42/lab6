package lab5.commands;

import lab5.managers.ConsoleManager;
import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 * Удаляет элемент из коллекции по его ключу
 * @author Alina
 */
public class RemoveKey extends Command{
    private final Runner runner;

    public RemoveKey(Runner runner) {
        super("remove_key", "удалить элемент из коллекции по его ключу", 1);
        this.runner = runner;
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        Integer key;
        try {
            key = Integer.valueOf(args[1]);
        } catch (NumberFormatException e) {
            ConsoleManager.printError("Введённый ключ не является числом типа Integer.");
            return ExitCode.ERROR;
        }
        if (!runner.collectionManager.getCollection().containsKey(key)) {
            ConsoleManager.printError("В коллекции нет элемента с ключом " + key);
            return ExitCode.ERROR;
        }
        runner.collectionManager.removeByKey(key);
        return ExitCode.OK;
    }
}
