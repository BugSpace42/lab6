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
        super("remove_key", "удалить элемент из коллекции по его ключу", 1, 1);
        this.runner = runner;
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        Integer key = Integer.valueOf(args[1]);
        runner.collectionManager.removeByKey(key);
        return ExitCode.OK;
    }
}
