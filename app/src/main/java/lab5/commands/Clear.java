package lab5.commands;

import lab5.exceptions.TooManyArgumentsException;
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
        try {
            if (args.length > 1) {
                throw new TooManyArgumentsException("Введено слишком много аргументов.");
            }
            runner.collectionManager.clearCollection();
            return Runner.ExitCode.OK;
        } catch (TooManyArgumentsException e) {
            runner.consoleManager.printError(e);
            return ExitCode.ERROR;
        } catch (Exception e) {
            runner.consoleManager.printError("Непредвиденная ошибка!");
            return ExitCode.ERROR;
        }
    }
}
