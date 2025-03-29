package lab5.commands;

import java.util.HashMap;

import lab5.entity.MusicBand;
import lab5.managers.ConsoleManager;
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
        super("info", "вывести информацию о коллекции", 0);
        this.runner = runner;
    }

    /**
     * Выполняет команду.
     */
    // todo
    @Override
    public Runner.ExitCode execute(String[] args) {
        try {
            HashMap<Integer, MusicBand> collection = runner.collectionManager.getCollection();
            ConsoleManager.println("Информация о коллекции:");
            ConsoleManager.println(" Тип коллекции: " + collection.getClass().getName());
            ConsoleManager.println(" Дата инициализации коллекции: " + runner.collectionManager.getInitDate());
            ConsoleManager.println(" Количество элементов: " + collection.size());
            return Runner.ExitCode.OK;
        } catch (Exception e) {
            ConsoleManager.printError("Непредвиденная ошибка!");
            return ExitCode.ERROR;
        }
    }
}
