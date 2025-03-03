package lab5.commands;

import java.io.IOException;

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
        super("save", "сохранить коллекцию в файл");
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
        try {
            runner.fileManager.writeCollection(runner.collectionManager.getCollection());
        } catch (IOException e) {
            runner.consoleManager.printError("Невозможно сохранить коллекцию в файл.");
            return ExitCode.ERROR;
        }
        return ExitCode.OK;
    }
}
