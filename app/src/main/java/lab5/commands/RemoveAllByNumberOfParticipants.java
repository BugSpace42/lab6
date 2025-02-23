package lab5.commands;

import java.util.HashMap;
import java.util.Map;

import lab5.entity.MusicBand;
import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 * Удаляет из коллекции все элементы, значение поля numberOfParticipants которого эквивалентно заданному.
 * @author Alina
 */
public class RemoveAllByNumberOfParticipants extends Command{
    private final Runner runner;

    public RemoveAllByNumberOfParticipants(Runner runner) {
        super("remove_all_by_number_of_participants", 
              "удалить из коллекции все элементы, значение поля numberOfParticipants которого эквивалентно заданному");
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
        Integer numberOfParticipants = runner.consoleManager.askMusicBandNumber();

        // ссылка на коллекцию, которую будем изменять
        HashMap<Integer, MusicBand> collection = runner.collectionManager.getCollection();
        
        boolean isRemoved = false;
        for (Map.Entry<Integer, MusicBand> entry : collection.entrySet()) {
            if (entry.getValue().getNumberOfParticipants().equals(numberOfParticipants)) {
                runner.collectionManager.removeByKey(entry.getKey());
                runner.consoleManager.println("Удалён элемент с ключом " + entry.getKey());
                isRemoved = true;
            }
        }
        if (!isRemoved) {
            runner.consoleManager.println("Не найдено элементов с заданным полем bestAlbum.");
        }
        return ExitCode.OK;
    }

}
