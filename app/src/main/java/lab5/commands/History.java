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
        super("history", "вывести последние 8 команд");
        this.runner = runner;
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        if (args.length > 1) {
            runner.consoleManager.printError("Введено слишком много аргументов.");
            return ExitCode.ERROR;
        }
        int numberOfCommands = 8;
        ArrayList<String> history = runner.commandManager.getCommandHistory();
        runner.consoleManager.println("История команд (начиная с последней):");
        for (int i = 0; i < Math.min(numberOfCommands, history.size()); i++) {
            runner.consoleManager.println(history.get(history.size()-i-1));
        }
        return ExitCode.OK;
    }
}
