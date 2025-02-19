package lab5.commands;

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
        if (args.length > 1) {
            runner.consoleManager.printError("Введено слишком много аргументов.");
            return ExitCode.ERROR;
        }
        return Runner.ExitCode.EXIT;
    }
}
