package lab5;

import java.nio.file.Path;

import lab5.commands.Clear;
import lab5.commands.ExecuteScript;
import lab5.commands.Exit;
import lab5.commands.Help;
import lab5.commands.History;
import lab5.commands.Info;
import lab5.commands.Insert;
import lab5.commands.PrintFieldDescendingNumberOfParticipants;
import lab5.commands.RemoveAllByNumberOfParticipants;
import lab5.commands.RemoveAnyByBestAlbum;
import lab5.commands.RemoveGreater;
import lab5.commands.RemoveKey;
import lab5.commands.ReplaceIfGreater;
import lab5.commands.Save;
import lab5.commands.Show;
import lab5.commands.Update;
import lab5.managers.CommandManager;
import lab5.managers.ConsoleManager;
import lab5.managers.FileManager;
import lab5.utility.Runner;

public class Main {
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
        //commandManager.newCommand(new ShowTable());
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
