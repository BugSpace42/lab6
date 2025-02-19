/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package lab5.commands;

import lab5.entity.MusicBand;
import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 *
 * @author Alina
 */
public class Update extends Command{
    private final Runner runner;

    public Update(Runner runner){
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.runner = runner;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public Runner.ExitCode execute(String[] args) {
        Long id;
        if (args.length > 2) {
            runner.consoleManager.printError("Введено слишком много аргументов.");
            return ExitCode.ERROR;
        }
        if (args.length < 2) {
            runner.consoleManager.printError("Не введён id элемента.");
            return ExitCode.ERROR;
        }
        try {
            id = Long.valueOf(args[1]);
        } catch (NumberFormatException e) {
            runner.consoleManager.printError("Введённый id не является числом типа Long.");
            return ExitCode.ERROR;
        }
        MusicBand element = runner.consoleManager.askMusicBand();
        runner.collectionManager.updateElementById(id, element);
        return ExitCode.OK;
    }
}
