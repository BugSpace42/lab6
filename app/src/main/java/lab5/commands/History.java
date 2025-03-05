/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package lab5.commands;

import java.util.ArrayList;

import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 * Выводит последние 8 команд (без их аргументов)
 * @author Alina
 */
public class History extends Command{
    private final Runner runner;

    public History(Runner runner) {
        super("history", "вывести последние 8 команд", 0);
        this.runner = runner;
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        try {
            int numberOfCommands = 8;
            ArrayList<String> history = runner.commandManager.getCommandHistory();
            if (history.isEmpty()) {
                runner.consoleManager.println("История команд пуста.");
                return Runner.ExitCode.OK;
            }
            runner.consoleManager.println("История команд (начиная с последней):");
            for (int i = 0; i < Math.min(numberOfCommands, history.size()); i++) {
                runner.consoleManager.println(history.get(history.size()-i-1));
            }
            return Runner.ExitCode.OK;
        } catch (Exception e) {
            runner.consoleManager.printError("Непредвиденная ошибка!");
            return ExitCode.ERROR;
        }
    }
}
