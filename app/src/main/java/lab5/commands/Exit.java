package lab5.commands;

import lab5.exceptions.TooManyArgumentsException;
import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 *
 * @author Alina
 */
public class Exit extends Command{
    private final Runner runner;

    public Exit(Runner runner) {
        super("exit", "завершить программу (без сохранения в файл)");
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
            return Runner.ExitCode.EXIT;
        } catch (TooManyArgumentsException e) {
            runner.consoleManager.printError(e);
            return ExitCode.ERROR;
        } catch (Exception e) {
            runner.consoleManager.printError("Непредвиденная ошибка!");
            return ExitCode.ERROR;
        }
    }
}
