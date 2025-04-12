/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package lab6.commands;

import lab6.entity.MusicBand;
import lab6.exceptions.CanceledCommandException;
import lab6.managers.CollectionManager;
import lab6.managers.ConsoleManager;
import lab6.utility.Command;
import lab6.utility.Runner;
import lab6.utility.Runner.ExitCode;

/**
 *
 * @author Alina
 */
public class Update extends Command{
    public Update(){
        super("update", "обновить значение элемента коллекции, id которого равен заданному", 1);
    }

    /**
     * Выполняет команду.
     */
    @Override
    public Runner.ExitCode execute(String[] args) {
        CollectionManager collectionManager = CollectionManager.getCollectionManager();
        Long id;
        try {
            id = Long.valueOf(args[1]);
        } catch (NumberFormatException e) {
            ConsoleManager.printError("Введённый id не является числом типа Long.");
            return ExitCode.ERROR;
        }
        if (collectionManager.getById(id) == null) {
            ConsoleManager.printError("В коллекции нет элемента с id " + id);
            return ExitCode.ERROR;
        }
        MusicBand element;
        try {
            element = MusicBand.askMusicBand();
            collectionManager.updateElementById(id, element);
            return ExitCode.OK;
        } catch (CanceledCommandException e) {
            ConsoleManager.println(e.getMessage());
            return ExitCode.CANCEL;
        }
    }
}
