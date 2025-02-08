package lab5.commands;

import lab5.entity.MusicBand;
import lab5.managers.CollectionManager;

/**
 * Добавить новый элемент с заданным ключом
 * @author Alina
 */
public class Insert implements Command{
    private CollectionManager collectionManager;
    private MusicBand element;
    private Integer key;

    public Insert(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setTheElement(MusicBand element) {
        this.element = element;
    }

    public void setTheKey(Integer key) {
        this.key = key;
    }
    
    @Override
    public void execute(){
        collectionManager.addToCollection(key, element);
    }
}
