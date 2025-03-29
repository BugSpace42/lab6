package lab5.commands;

import java.io.IOException;

import lab5.managers.ConsoleManager;
import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 * Сохраняет коллекцию в файл.
 * @author Alina
 */
public class Save extends Command{
    private final Runner runner;

    public Save(Runner runner) {
        super("save", "сохранить коллекцию в файл", 0);
        this.runner = runner;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public Runner.ExitCode execute(String[] args) {
        try {
            runner.fileManager.writeCollection(runner.collectionManager.getCollection());
        } catch (IOException e) {
            ConsoleManager.printError("Невозможно сохранить коллекцию в файл.");
            return ExitCode.ERROR;
        }
        return ExitCode.OK;
    }
}
