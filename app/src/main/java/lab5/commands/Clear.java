package lab5.commands;

import lab5.managers.CollectionManager;

/**
 * Очищает коллекцию.
 * @author Alina
 */
public class Clear implements Command{
    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
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
