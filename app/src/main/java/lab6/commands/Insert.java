package lab6.commands;

import lab6.entity.MusicBand;
import lab6.exceptions.CanceledCommandException;
import lab6.managers.ConsoleManager;
import lab6.utility.Command;
import lab6.utility.Runner;
import lab6.utility.Runner.ExitCode;

/**
 * Добавляет в коллекцию новый элемент с заданным ключом.
 * @author Alina
 */
public class Insert extends Command{
    public Insert() {
        super("insert", "добавить новый элемент с заданным ключом", 1);
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        Runner runner = Runner.getRunner();
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
        
        MusicBand element;
        try {
            element = MusicBand.askMusicBand();
            runner.collectionManager.addToCollection(key, element);
            return ExitCode.OK;
        } catch (CanceledCommandException e) {
            ConsoleManager.println(e.getMessage());
            return ExitCode.CANCEL;
        }
    }
}
