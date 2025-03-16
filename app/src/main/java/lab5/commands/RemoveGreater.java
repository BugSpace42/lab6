package lab5.commands;

import java.util.HashMap;
import java.util.Map;

import lab5.entity.MusicBand;
import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 * Удаляет из коллекции все элементы, превышающие заданный.
 * @author Alina
 */
public class RemoveGreater extends Command{
    private final Runner runner;

    public RemoveGreater(Runner runner) {
        super("remove_greater", "удалить из коллекции все элементы, превышающие заданный", 0);
        this.runner = runner;
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        MusicBand musicBand = MusicBand.askMusicBand();

        // ссылка на коллекцию
        HashMap<Integer, MusicBand> collection = runner.collectionManager.getCollection();
        
        boolean isRemoved = false;
        for (Map.Entry<Integer, MusicBand> entry : collection.entrySet()) {
            if (entry.getValue().compareTo(musicBand) > 0) {
                runner.collectionManager.removeByKey(entry.getKey());
                runner.consoleManager.println("Удалён элемент с ключом " + entry.getKey());
                isRemoved = true;
                break;
            }
        }
        if (!isRemoved) {
            runner.consoleManager.println("Не найдено элементов, больших заданного.");
        }
        return ExitCode.OK;
    }
}
