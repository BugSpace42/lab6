package lab5.commands;

import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 * Очищает коллекцию.
 * @author Alina
 */
public class Clear extends Command{
    private final Runner runner;

    public Clear(Runner runner) {
        super("clear", "очистить коллекцию");
        this.runner = runner;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public Runner.ExitCode execute(String[] args) {
        if (args.length > 1) {
            runner.consoleManager.printError("Введено слишком много аргументов.");
            return ExitCode.ERROR;
        }
        runner.collectionManager.clearCollection();
        return Runner.ExitCode.OK;
    }
}
