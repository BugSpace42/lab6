package lab5.commands;

import java.util.HashMap;
import java.util.Map;

import lab5.entity.Album;
import lab5.entity.MusicBand;
import lab5.managers.CollectionManager;
import lab5.managers.ConsoleManager;
import lab5.utility.Command;
import lab5.utility.Runner.ExitCode;

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
        Album album = Album.askAlbum();

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
