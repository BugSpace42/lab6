package lab5;

import java.util.Scanner;

import lab5.commands.Clear;
import lab5.commands.Insert;
import lab5.managers.CollectionManager;
import lab5.managers.CommandManager;
import lab5.managers.ConsoleManager;
import lab5.utility.StandardConsole;

public class Main {
    public static CollectionManager collectionManager = new CollectionManager();
    public static CommandManager commandManager = new CommandManager();
    public static ConsoleManager consoleManager = new ConsoleManager(new StandardConsole(), new Scanner(System.in));

    public static void main(String[] args) {
        commandManager.newCommand("insert", new Insert(collectionManager));
        commandManager.newCommand("clear", new Clear(collectionManager));
        consoleManager.setCommands(commandManager.getCommandList());
        consoleManager.greeting();

        /*
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
        */
    }
}
