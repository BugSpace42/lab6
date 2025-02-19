package lab5.commands;

import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 * Удаляет из коллекции все элементы, превышающие заданный.
 * @author Alina
 */
public class RemoveGreater extends Command{
    private final Runner runner;

    public RemoveGreater(Runner runner) {
        super("remove_greater", "удалить из коллекции все элементы, превышающие заданный");
        this.runner = runner;
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        // todo
        return ExitCode.ERROR;
    }

}
