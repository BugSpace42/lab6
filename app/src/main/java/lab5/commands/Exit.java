package lab5.commands;

import lab5.utility.Command;
import lab5.utility.Runner;

/**
 *
 * @author Alina
 */
public class Exit extends Command{
    private final Runner runner;

    public Exit(Runner runner) {
        super("exit", "завершить программу (без сохранения в файл)", 0, 0);
        this.runner = runner;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public Runner.ExitCode execute(String[] args) {
        return Runner.ExitCode.EXIT;
    }
}
