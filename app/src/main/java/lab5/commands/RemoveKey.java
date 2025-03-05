package lab5.commands;

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
        super("remove_key", "удалить элемент из коллекции по его ключу");
        this.runner = runner;
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        Integer key;
        if (args.length > 2) {
            runner.consoleManager.printError("Введено слишком много аргументов.");
            return ExitCode.ERROR;
        }
        if (args.length < 2) {
            runner.consoleManager.printError("Не введён ключ элемента.");
            return ExitCode.ERROR;
        }
        try {
            key = Integer.valueOf(args[1]);
        } catch (NumberFormatException e) {
            runner.consoleManager.printError("Введённый ключ не является числом типа Integer.");
            return ExitCode.ERROR;
        }
        if (!runner.collectionManager.getCollection().containsKey(key)) {
            runner.consoleManager.printError("В коллекции нет элемента с ключом " + key);
            return ExitCode.ERROR;
        }
        runner.collectionManager.removeByKey(key);
        return ExitCode.OK;
    }
}
