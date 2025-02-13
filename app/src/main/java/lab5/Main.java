package lab5;

import java.util.Scanner;

import lab5.commands.Clear;
import lab5.commands.Exit;
import lab5.commands.Insert;
import lab5.managers.CollectionManager;
import lab5.managers.CommandManager;
import lab5.managers.ConsoleManager;
import lab5.utility.Runner;
import lab5.utility.StandardConsole;

public class Main {
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager();
        ConsoleManager consoleManager = new ConsoleManager(new StandardConsole(), new Scanner(System.in));
        Runner runner = new Runner(commandManager, consoleManager, collectionManager);

        commandManager.newCommand(new Insert(runner));
        commandManager.newCommand(new Clear(runner));
        commandManager.newCommand(new Exit(runner));
        consoleManager.setCommands(commandManager.getCommandList());
        
        runner.run();

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
