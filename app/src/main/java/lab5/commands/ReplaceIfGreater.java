package lab5.commands;

import java.util.HashMap;

import lab5.entity.MusicBand;
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
        super("replace_if_greater", "заменить значение по ключу, если новое значение больше старого");
        this.runner = runner;
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        Integer key;
        if (args.length > 2) {
            runner.consoleManager.printError("Введено слишком много аргументов.");
            return ExitCode.ERROR;
        }
        if (args.length < 2) {
            runner.consoleManager.printError("Не введён ключ элемента.");
            return ExitCode.ERROR;
        }
        try {
            key = Integer.valueOf(args[1]);
        } catch (NumberFormatException e) {
            runner.consoleManager.printError("Введённый ключ не является числом типа Integer.");
            return ExitCode.ERROR;
        }
        MusicBand musicBand = runner.consoleManager.askMusicBand();

        // ссылка на коллекцию
        HashMap<Integer, MusicBand> collection = runner.collectionManager.getCollection();
        
        if (musicBand.compareTo(collection.get(key)) > 0) {
            runner.collectionManager.addToCollection(key, musicBand);
            runner.consoleManager.println("Элемент по введённому ключу заменён на заданный.");
        }
        else{
            runner.consoleManager.println("Элемент по введённому больше, чем заданный.");
            runner.consoleManager.println("Элемент по введённому ключу не изменён.");
        }
        return ExitCode.OK;
    }
}
