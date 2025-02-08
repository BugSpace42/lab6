package lab5;

import lab5.commands.*;
import lab5.entity.*;
import lab5.managers.*;

public class Main {
    public static CollectionManager collectionManager = new CollectionManager();
    public static CommandManager commandManager = new CommandManager();

    public static void main(String[] args) {
        commandManager.newCommand("insert", new Insert(collectionManager));
        commandManager.newCommand("clear", new Clear(collectionManager));

        Coordinates firstCoordinates = new Coordinates(123, 321);
        Album firstAlbum = new Album("Some Album", 100500.0);

        MusicBand firstBand = new MusicBand(Long.valueOf(1), "Band", firstCoordinates, 
                                null, 3, MusicGenre.JAZZ, firstAlbum);

        System.out.println(collectionManager.toString());
        insert.setTheElement(firstBand);
        insert.setTheKey(0);
        insert.execute();
        System.out.println("    Добавили элемент: " + collectionManager.toString());
        System.out.println("    Нашли по ID: " + collectionManager.getById(Long.valueOf(1)));
    }
}
