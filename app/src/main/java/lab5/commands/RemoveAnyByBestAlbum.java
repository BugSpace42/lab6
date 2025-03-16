package lab5.commands;

import java.util.HashMap;
import java.util.Map;

import lab5.entity.Album;
import lab5.entity.MusicBand;
import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 * Удаляет из коллекции один элемент, значение поля bestAlbum которого эквивалентно заданному.
 * @author Alina
 */
public class RemoveAnyByBestAlbum extends Command{
    private final Runner runner;

    public RemoveAnyByBestAlbum(Runner runner) {
        super("remove_any_by_best_album ", 
              "удалить из коллекции один элемент, значение поля bestAlbum которого эквивалентно заданному", 0);
        this.runner = runner;
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        Album album = Album.askAlbum();

        // ссылка на коллекцию, которую будем изменять
        HashMap<Integer, MusicBand> collection = runner.collectionManager.getCollection();
        
        boolean isRemoved = false;
        for (Map.Entry<Integer, MusicBand> entry : collection.entrySet()) {
            if (entry.getValue().getBestAlbum().equals(album)) {
                runner.collectionManager.removeByKey(entry.getKey());
                runner.consoleManager.println("Удалён элемент с ключом " + entry.getKey());
                isRemoved = true;
                break;
            }
        }
        if (!isRemoved) {
            runner.consoleManager.println("Не найдено элементов с заданным полем bestAlbum.");
        }
        return ExitCode.OK;
    }
}
