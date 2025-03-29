package lab5.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import lab5.entity.MusicBand;
import lab5.managers.ConsoleManager;
import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 * Выводит значения поля numberOfParticipants всех элементов в порядке убывания.
 * @author Alina
 */
public class PrintFieldDescendingNumberOfParticipants extends Command{
    private final Runner runner;

    public PrintFieldDescendingNumberOfParticipants(Runner runner) {
        super("print_field_descending_number_of_participants", 
              "вывести значения поля numberOfParticipants всех элементов в порядке убывания", 0);
        this.runner = runner;
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        try {
            if (runner.collectionManager.getCollection().isEmpty()) {
                ConsoleManager.println("Коллекция пуста.");
                return ExitCode.OK;
            }

            ArrayList<Integer> numberOfParticipantsList = new ArrayList<>();
        
            for (Map.Entry<Integer, MusicBand> entry : runner.collectionManager.getCollection().entrySet()) {
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
