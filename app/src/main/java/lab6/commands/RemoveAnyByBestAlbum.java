package lab6.commands;

import java.util.HashMap;
import java.util.Map;

import lab6.entity.Album;
import lab6.entity.MusicBand;
import lab6.exceptions.CanceledCommandException;
import lab6.managers.CollectionManager;
import lab6.managers.ConsoleManager;
import lab6.utility.Command;
import lab6.utility.Runner.ExitCode;

/**
 * Удаляет из коллекции один элемент, значение поля bestAlbum которого эквивалентно заданному.
 * @author Alina
 */
public class RemoveAnyByBestAlbum extends Command{
    public RemoveAnyByBestAlbum() {
        super("remove_any_by_best_album", 
              "удалить из коллекции один элемент, значение поля bestAlbum которого эквивалентно заданному", 0);
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        CollectionManager collectionManager = CollectionManager.getCollectionManager();
        Album album;
        try {
            album = Album.askAlbum();
        } catch (CanceledCommandException e) {
            ConsoleManager.println(e.getMessage());
            return ExitCode.CANCEL;
        }

        // ссылка на коллекцию, которую будем изменять
        HashMap<Integer, MusicBand> collection = collectionManager.getCollection();
        
        boolean isRemoved = false;
        for (Map.Entry<Integer, MusicBand> entry : collection.entrySet()) {
            if (album.equals(entry.getValue().getBestAlbum())) {
                collectionManager.removeByKey(entry.getKey());
                ConsoleManager.println("Удалён элемент с ключом " + entry.getKey());
                isRemoved = true;
                break;
            }
        }
        if (!isRemoved) {
            ConsoleManager.println("Не найдено элементов с заданным полем bestAlbum.");
        }
        return ExitCode.OK;
    }
}
