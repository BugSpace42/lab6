package lab5.commands;

import java.util.HashMap;

import lab5.entity.MusicBand;
import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 * Выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
 * @author Alina
 */
public class Info extends Command{
    private final Runner runner;

    public Info(Runner runner) {
        super("info", "вывести информацию о коллекции");
        this.runner = runner;
    }

    /**
     * Выполняет команду.
     */
    // todo
    @Override
    public Runner.ExitCode execute(String[] args) {
        if (args.length > 1) {
            runner.consoleManager.printError("Введено слишком много аргументов.");
            return ExitCode.ERROR;
        }
        HashMap<Integer, MusicBand> collection = runner.collectionManager.getCollection();
        runner.consoleManager.println("Информация о коллекции:");
        runner.consoleManager.println(" Тип коллекции: " + collection.getClass().getName());
        runner.consoleManager.println(" Количество элементов: " + collection.size());
        return Runner.ExitCode.OK;
    }
}
