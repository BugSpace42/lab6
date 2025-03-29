package lab5.commands;

import lab5.managers.ConsoleManager;
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
        super("exit", "завершить программу (без сохранения в файл)", 0);
        this.runner = runner;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public Runner.ExitCode execute(String[] args) {
        try {
            return Runner.ExitCode.EXIT;
        } catch (Exception e) {
            ConsoleManager.printError("Непредвиденная ошибка!");
            return ExitCode.ERROR;
        }
    }
}
