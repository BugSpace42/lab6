package lab6.commands;

import lab6.managers.ConsoleManager;
import lab6.utility.Command;
import lab6.utility.Runner;
import lab6.utility.Runner.ExitCode;

/**
 * Завершает программу без сохранения в файл.
 * @author Alina
 */
public class Exit extends Command{

    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)", 0);
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
