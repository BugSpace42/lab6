package lab6.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import lab6.entity.MusicBand;
import lab6.managers.CollectionManager;
import lab6.managers.ConsoleManager;
import lab6.utility.Command;
import lab6.utility.Runner.ExitCode;

/**
 * Выводит значения поля numberOfParticipants всех элементов в порядке убывания.
 * @author Alina
 */
public class PrintFieldDescendingNumberOfParticipants extends Command{
    public PrintFieldDescendingNumberOfParticipants() {
        super("print_field_descending_number_of_participants", 
              "вывести значения поля numberOfParticipants всех элементов в порядке убывания", 0);
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        CollectionManager collectionManager = CollectionManager.getCollectionManager();
        try {
            if (collectionManager.getCollection().isEmpty()) {
                ConsoleManager.println("Коллекция пуста.");
                return ExitCode.OK;
            }

            ArrayList<Integer> numberOfParticipantsList = new ArrayList<>();
        
            for (Map.Entry<Integer, MusicBand> entry : collectionManager.getCollection().entrySet()) {
                numberOfParticipantsList.add(entry.getValue().getNumberOfParticipants());
            }

            Collections.sort(numberOfParticipantsList, Collections.reverseOrder());
            ConsoleManager.println("Значения поля numberOfParticipants всех элементов в порядке убывания:");
            for (Integer number : numberOfParticipantsList) {
                ConsoleManager.print(number + " ");
            }
            ConsoleManager.print("\n");
            return ExitCode.OK;
        } catch (Exception e) {
            ConsoleManager.printError("Непредвиденная ошибка!");
            return ExitCode.ERROR;
        }
    }
}
