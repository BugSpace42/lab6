package lab5.commands;

import lab5.managers.CollectionManager;
import lab5.utility.Command;

/**
 * Очищает коллекцию.
 * @author Alina
 */
public class Clear extends Command{
    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию", 0, 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public void execute(String[] args) {
        collectionManager.clearCollection();
    }
}
