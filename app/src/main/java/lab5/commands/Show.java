package lab5.commands;

import java.util.Map;

import lab5.entity.MusicBand;
import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 * Выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 * @author Alina
 */
public class Show extends Command {
    private final Runner runner;

    public Show(Runner runner){
        super("show", "вывести все элементы коллекции в строковом представлении");
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
        for (Map.Entry<Integer, MusicBand> elem : runner.collectionManager.getCollection().entrySet()) {
            runner.consoleManager.println(elem.getValue());
        }
        return Runner.ExitCode.OK;
    }
}
