package lab5.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import lab5.entity.MusicBand;
import lab5.exceptions.TooManyArgumentsException;
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
              "вывести значения поля numberOfParticipants всех элементов в порядке убывания");
        this.runner = runner;
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        try {
            if (args.length > 1) {
                throw new TooManyArgumentsException("Введено слишком много аргументов.");
            }
            if (runner.collectionManager.getCollection().isEmpty()) {
                runner.consoleManager.println("Коллекция пуста.");
                return ExitCode.OK;
            }

            ArrayList<Integer> numberOfParticipantsList = new ArrayList<>();
        
            for (Map.Entry<Integer, MusicBand> entry : runner.collectionManager.getCollection().entrySet()) {
                numberOfParticipantsList.add(entry.getValue().getNumberOfParticipants());
            }

            Collections.sort(numberOfParticipantsList, Collections.reverseOrder());
            runner.consoleManager.println("Значения поля numberOfParticipants всех элементов в порядке убывания:");
            for (Integer number : numberOfParticipantsList) {
                runner.consoleManager.print(number + " ");
            }
            runner.consoleManager.print("\n");
            return ExitCode.OK;
        } catch (TooManyArgumentsException e) {
            runner.consoleManager.printError(e);
            return ExitCode.ERROR;
        } catch (Exception e) {
            runner.consoleManager.printError("Непредвиденная ошибка!");
            return ExitCode.ERROR;
        }
    }
}
