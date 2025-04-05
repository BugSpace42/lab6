/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package lab5.commands;

import lab5.entity.MusicBand;
import lab5.exceptions.CanceledCommandException;
import lab5.managers.CollectionManager;
import lab5.managers.ConsoleManager;
import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

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
