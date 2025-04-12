package lab6;

import java.nio.file.Path;

import lab6.commands.Clear;
import lab6.commands.ExecuteScript;
import lab6.commands.Exit;
import lab6.commands.Help;
import lab6.commands.History;
import lab6.commands.Info;
import lab6.commands.Insert;
import lab6.commands.PrintFieldDescendingNumberOfParticipants;
import lab6.commands.RemoveAllByNumberOfParticipants;
import lab6.commands.RemoveAnyByBestAlbum;
import lab6.commands.RemoveGreater;
import lab6.commands.RemoveKey;
import lab6.commands.ReplaceIfGreater;
import lab6.commands.Save;
import lab6.commands.Show;
import lab6.commands.Update;
import lab6.managers.CommandManager;
import lab6.managers.ConsoleManager;
import lab6.managers.FileManager;
import lab6.utility.Runner;

/**
 * Основной класс.
 */
public class Main {
    /**
     * Основной метод
     * @param args название файла с загружаемой коллекцией
     */
    public static void main(String[] args) {
        CommandManager commandManager = CommandManager.getCommandManager();
        
        String defaultFilePath = "collection.csv";
        Path filePath;
        if (args.length == 0) {
            ConsoleManager.println("Внимание! Не введено название файла с загружаемой коллекцией.");
            ConsoleManager.println("Будет загружена коллекция по умолчанию из файла " + defaultFilePath);
            filePath = Path.of(defaultFilePath);
        }
        else {
            filePath = Path.of(args[0]);
        }

        FileManager fileManager = new FileManager(filePath.toAbsolutePath());
        Runner.setFileManager(fileManager);
        Runner runner = Runner.getRunner();

        commandManager.newCommand(new Help());
        commandManager.newCommand(new Info());
        commandManager.newCommand(new Show());
        commandManager.newCommand(new Insert());
        commandManager.newCommand(new Update());
        commandManager.newCommand(new RemoveKey());
        commandManager.newCommand(new Clear());
        commandManager.newCommand(new Save());
        commandManager.newCommand(new ExecuteScript());
        commandManager.newCommand(new Exit());
        commandManager.newCommand(new RemoveGreater());
        commandManager.newCommand(new History());
        commandManager.newCommand(new ReplaceIfGreater());
        commandManager.newCommand(new RemoveAllByNumberOfParticipants());
        commandManager.newCommand(new RemoveAnyByBestAlbum());
        commandManager.newCommand(new PrintFieldDescendingNumberOfParticipants());
        
        runner.run();
    }
}
