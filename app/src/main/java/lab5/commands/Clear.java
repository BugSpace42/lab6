package lab5.commands;

import lab5.utility.Command;
import lab5.utility.Runner;

/**
 * Очищает коллекцию.
 * @author Alina
 */
public class Clear extends Command{
    private final Runner runner;

    public Clear(Runner runner) {
        super("clear", "очистить коллекцию", 0, 0);
        this.runner = runner;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public Runner.ExitCode execute(String[] args) {
        runner.collectionManager.clearCollection();
        return Runner.ExitCode.OK;
    }
}
