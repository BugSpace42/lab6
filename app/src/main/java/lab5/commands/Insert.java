package lab5.commands;

import lab5.entity.MusicBand;
import lab5.managers.ConsoleManager;
import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 * Добавляет в коллекцию новый элемент с заданным ключом.
 * @author Alina
 */
public class Insert extends Command{
    private final Runner runner;

    public Insert(Runner runner) {
        super("insert", "добавить новый элемент с заданным ключом", 1);
        this.runner = runner;
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        Integer key;
        try {
            key = Integer.valueOf(args[1]);
        } catch (NumberFormatException e) {
            ConsoleManager.printError("Введённый ключ не является числом типа Integer.");
            return ExitCode.ERROR;
        }
        if (runner.collectionManager.getCollection().containsKey(key)) {
            ConsoleManager.printError("В коллекции уже есть элемент с ключом " + key);
            return ExitCode.ERROR;
        }
        
        MusicBand element = MusicBand.askMusicBand();
        runner.collectionManager.addToCollection(key, element);
        return ExitCode.OK;
    }
}
