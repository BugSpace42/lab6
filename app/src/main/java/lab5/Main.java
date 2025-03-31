package lab5;

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
import lab5.commands.ShowTable;
import lab5.commands.Update;
import lab5.managers.CommandManager;
import lab5.managers.ConsoleManager;
import lab5.managers.FileManager;
import lab5.utility.Runner;

public class Main {
    public static void main(String[] args) {
        CommandManager commandManager = new CommandManager();
        ConsoleManager consoleManager = new ConsoleManager();
        String defaultFilePath = "app\\src\\test\\resources\\table1.csv";
        String filePath;
        if (args.length == 0) {
            ConsoleManager.println("Внимание! Не введено название файла с загружаемой коллекцией.");
            ConsoleManager.println("Будет загружена коллекция по умолчанию из файла " + defaultFilePath);
            filePath = defaultFilePath;
        }
        else {
            filePath = args[0];
        }

        FileManager fileManager = new FileManager(filePath);
        Runner runner = new Runner(commandManager, consoleManager, fileManager);

        commandManager.newCommand(new Help(runner));
        commandManager.newCommand(new Info(runner));
        commandManager.newCommand(new Show(runner));
        commandManager.newCommand(new ShowTable(runner));
        commandManager.newCommand(new Insert(runner));
        commandManager.newCommand(new Update(runner));
        commandManager.newCommand(new RemoveKey(runner));
        commandManager.newCommand(new Clear(runner));
        commandManager.newCommand(new Save(runner));
        commandManager.newCommand(new ExecuteScript(runner));
        commandManager.newCommand(new Exit(runner));
        commandManager.newCommand(new RemoveGreater(runner));
        commandManager.newCommand(new History(runner));
        commandManager.newCommand(new ReplaceIfGreater(runner));
        commandManager.newCommand(new RemoveAllByNumberOfParticipants(runner));
        commandManager.newCommand(new RemoveAnyByBestAlbum(runner));
        commandManager.newCommand(new PrintFieldDescendingNumberOfParticipants(runner));
        
        runner.run();
    }
}
