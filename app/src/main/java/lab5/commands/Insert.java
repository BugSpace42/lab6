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
    private Runner runner;

    public Insert(Runner runner) {
        super("insert", "добавить новый элемент с заданным ключом", 1, 1);
        this.runner = runner;
    }
    
    /**
     * Выполняет команду.
     */
    @Override
    public ExitCode execute(String[] args){
        Integer key = Integer.valueOf(args[1]);
        MusicBand element = runner.consoleManager.askMusicBand();
        runner.collectionManager.addToCollection(key, element);
        return ExitCode.OK;
    }
}
