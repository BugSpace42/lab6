package lab5.commands;

import java.util.HashMap;

import lab5.entity.MusicBand;
import lab5.exceptions.TooManyArgumentsException;
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
        try {
            if (args.length > 1) {
                throw new TooManyArgumentsException("Введено слишком много аргументов.");
            }
            HashMap<Integer, MusicBand> collection = runner.collectionManager.getCollection();
            runner.consoleManager.println("Информация о коллекции:");
            runner.consoleManager.println(" Тип коллекции: " + collection.getClass().getName());
            runner.consoleManager.println(" Дата инициализации коллекции: " + runner.collectionManager.getInitDate());
            runner.consoleManager.println(" Количество элементов: " + collection.size());
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
