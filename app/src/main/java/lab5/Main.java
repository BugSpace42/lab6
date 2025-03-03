package lab5;

import java.util.Scanner;

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
import lab5.utility.StandardConsole;

public class Main {
    public static void main(String[] args) {

        String filePath = "d:\\Users\\Alina\\Desktop\\ITMO\\Программирование\\Лаба 5\\table.txt";

        CommandManager commandManager = new CommandManager();
        ConsoleManager consoleManager = new ConsoleManager(new StandardConsole(), new Scanner(System.in));
        FileManager fileManager = new FileManager(filePath);
        Runner runner = new Runner(commandManager, consoleManager, fileManager);

        commandManager.newCommand(new Help(runner));
        commandManager.newCommand(new Info(runner));
        commandManager.newCommand(new Show(runner));
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
