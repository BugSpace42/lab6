package lab5.commands;

import java.util.Map;

import lab5.entity.MusicBand;
import lab5.utility.Command;
import lab5.utility.Runner;

/**
 * Выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 * @author Alina
 */
public class Show extends Command {
    private final Runner runner;

    public Show(Runner runner){
        super("show", "вывести все элементы коллекции в строковом представлении", 0, 0);
        this.runner = runner;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public Runner.ExitCode execute(String[] args) {
        for (Map.Entry<Integer, MusicBand> elem : runner.collectionManager.getCollection().entrySet()) {
            runner.consoleManager.println(elem.getValue());
        }
        return Runner.ExitCode.OK;
    }
}
