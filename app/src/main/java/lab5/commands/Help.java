package lab5.commands;

import lab5.exceptions.TooManyArgumentsException;
import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;

/**
 * Выводит справку по доступным командам.
 * @author Alina
 */
public class Help extends Command{
    private final Runner runner;

    public Help(Runner runner) {
        super("help", "вывести справку по доступным командам");
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
            runner.consoleManager.println("Доступные команды:");
            for (Command command : runner.commandManager.getCommands().values()) {
                runner.consoleManager.println(command.getName() + ": " + command.getDescription());
            }
            return Runner.ExitCode.OK;
        } catch (TooManyArgumentsException e) {
            runner.consoleManager.printError(e);
            return ExitCode.ERROR;
        } catch (Exception e) {
            runner.consoleManager.printError("Непредвиденная ошибка!");
            return ExitCode.ERROR;
        }
    }
}
