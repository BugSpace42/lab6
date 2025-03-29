package lab5.commands;

import java.util.HashMap;

import lab5.entity.MusicBand;
import lab5.managers.ConsoleManager;
import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 * Заменяет значение по ключу, если новое значение больше старого.
 * @author Alina
 */
public class ReplaceIfGreater extends Command{
    private final Runner runner;

    public ReplaceIfGreater(Runner runner) {
        super("replace_if_greater", "заменить значение по ключу, если новое значение больше старого", 1);
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
        if (!runner.collectionManager.getCollection().containsKey(key)) {
            ConsoleManager.printError("В коллекции нет элемента с ключом " + key);
            return ExitCode.ERROR;
        }
        MusicBand musicBand = MusicBand.askMusicBand();

        // ссылка на коллекцию
        HashMap<Integer, MusicBand> collection = runner.collectionManager.getCollection();
        
        if (musicBand.compareTo(collection.get(key)) > 0) {
            runner.collectionManager.addToCollection(key, musicBand);
            ConsoleManager.println("Элемент по введённому ключу заменён на заданный.");
        }
        else{
            ConsoleManager.println("Элемент по введённому больше, чем заданный.");
            ConsoleManager.println("Элемент по введённому ключу не изменён.");
        }
        return ExitCode.OK;
    }
}
