package lab6.commands;

import lab6.managers.ConsoleManager;
import lab6.utility.Command;
import lab6.utility.Runner;
import lab6.utility.Runner.ExitCode;

/**
 * Очищает коллекцию.
 * @author Alina
 */
public class Clear extends Command{

    public Clear() {
        super("clear", "очистить коллекцию", 0);
    }

    /**
     * Выполняет команду.
     */
    @Override
    public Runner.ExitCode execute(String[] args) {
        Runner runner = Runner.getRunner();
        try {
            runner.collectionManager.clearCollection();
            return Runner.ExitCode.OK;
        } catch (Exception e) {
            ConsoleManager.printError("Непредвиденная ошибка!");
            return ExitCode.ERROR;
        }
    }
}
