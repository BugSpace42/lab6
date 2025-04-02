package lab5.commands;

import java.util.HashMap;
import java.util.Map;

import lab5.entity.MusicBand;
import lab5.managers.CollectionManager;
import lab5.managers.ConsoleManager;
import lab5.utility.Command;
import lab5.utility.Runner.ExitCode;

/**
 * Удаляет из коллекции все элементы, превышающие заданный.
 * @author Alina
 */
public class RemoveGreater extends Command{
    public RemoveGreater() {
        super("remove_greater", "удалить из коллекции все элементы, превышающие заданный", 0);
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        CollectionManager collectionManager = CollectionManager.getCollectionManager();
        MusicBand musicBand = MusicBand.askMusicBand();

        // ссылка на коллекцию
        HashMap<Integer, MusicBand> collection = collectionManager.getCollection();
        
        boolean isRemoved = false;
        for (Map.Entry<Integer, MusicBand> entry : collection.entrySet()) {
            if (entry.getValue().compareTo(musicBand) > 0) {
                collectionManager.removeByKey(entry.getKey());
                ConsoleManager.println("Удалён элемент с ключом " + entry.getKey());
                isRemoved = true;
                break;
            }
        }
        if (!isRemoved) {
            ConsoleManager.println("Не найдено элементов, больших заданного.");
        }
        return ExitCode.OK;
    }
}
