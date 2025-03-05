package lab5.commands;

import lab5.entity.MusicBand;
import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 * Добавляет в коллекцию новый элемент с заданным ключом.
 * @author Alina
 */
public class Insert extends Command{
    private final Runner runner;

    public Insert(Runner runner) {
        super("insert", "добавить новый элемент с заданным ключом");
        this.runner = runner;
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        Integer key;
        if (args.length > 2) {
            runner.consoleManager.printError("Введено слишком много аргументов.");
            return ExitCode.ERROR;
        }
        if (args.length < 2) {
            runner.consoleManager.printError("Не введён ключ элемента.");
            return ExitCode.ERROR;
        }
        try {
            key = Integer.valueOf(args[1]);
        } catch (NumberFormatException e) {
            runner.consoleManager.printError("Введённый ключ не является числом типа Integer.");
            return ExitCode.ERROR;
        }
        if (runner.collectionManager.getCollection().containsKey(key)) {
            runner.consoleManager.printError("В коллекции уже есть элемент с ключом " + key);
            return ExitCode.ERROR;
        }
        
        MusicBand element = runner.consoleManager.askMusicBand();
        runner.collectionManager.addToCollection(key, element);
        return ExitCode.OK;
    }
}
