package lab5.commands;

import lab5.entity.MusicBand;
import lab5.managers.CollectionManager;
import lab5.utility.Command;

/**
 * Добавляет в коллекцию новый элемент с заданным ключом.
 * @author Alina
 */
public class Insert extends Command{
    private CollectionManager collectionManager;
    private MusicBand element;
    private Integer key;

    public Insert(CollectionManager collectionManager) {
        super("insert", "добавить новый элемент с заданным ключом", 1, 1);
        this.collectionManager = collectionManager;
    }

    public void setTheElement(MusicBand element) {
        this.element = element;
    }

    public void setTheKey(Integer key) {
        this.key = key;
    }
    
    public void execute(String[] args){
        collectionManager.addToCollection(key, element);
    }
}
