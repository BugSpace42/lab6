package lab5.managers;

import java.util.HashMap;

import lab5.entity.MusicBand;

/**
 * Класс, который оперирует коллекцией.
 * @author Alina
 */
public class CollectionManager {
    private HashMap<Integer, MusicBand> collection = new HashMap<Integer, MusicBand>();

    public CollectionManager() {}

    /**
    * @return Коллекция.
    */
    public HashMap<Integer, MusicBand> getCollection() {
        return collection;
    }

    public MusicBand getById(Long id) {
        for (HashMap.Entry<Integer, MusicBand> entry : collection.entrySet()) {
          if (entry.getValue().getId().equals(id)) return entry.getValue();
        }
        return null;
      }

    /**
     * Добавляет элемент в коллекцию.
     * @param musicBand - добавляемый объект
     */
    public void addToCollection(Integer key, MusicBand musicBand) {
        collection.put(key, musicBand);
    }

    /**
     * Обновляет элемент коллекции, id которого равен заданному.
     * @param id - id элемента, значение которого нужно обновить
     * @param musicBand - новое значение элемента
     */
    public void updateElementById(Long id, MusicBand musicBand) {
        for (HashMap.Entry<Integer, MusicBand> entry : collection.entrySet()) {
            if (entry.getValue().getId().equals(id)) collection.put(entry.getKey(), musicBand);
          }
    }

    /**
     * Удаляет элемент из коллекции по его ключу.
     * @param key - ключ элемента, который нужно удалить
     */
    public void removeByKey(Integer key) {
        collection.remove(key);
    }

    /**
     * Очищает коллекцию.
     */
    public void clearCollection() {
        collection.clear();
    }

    @Override
    public String toString() {
        return collection.toString();
    }
}
