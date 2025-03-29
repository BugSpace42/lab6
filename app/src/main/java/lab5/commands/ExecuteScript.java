package lab5.commands;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import lab5.exceptions.ScriptRecursionException;
import lab5.managers.ConsoleManager;
import lab5.utility.Command;
import lab5.utility.Runner;
import lab5.utility.Runner.ExitCode;
import lab5.utility.Runner.RunningMode;

/**
 * Считывает и исполнияет скрипт из указанного файла. 
 * В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
 * @author Alina
 */
public class ExecuteScript extends Command{
    private final Runner runner;

    public ExecuteScript(Runner runner) {
        super("execute_script", "считать и исполнить скрипт из указанного файла", 1);
        this.runner = runner;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public Runner.ExitCode execute(String[] args) {
        String scriptName = args[1];
        for (int i = 2; i < args.length; i++) {
            scriptName += " " + args[i];
        }
        RunningMode previousMode = runner.getCurrentMode();
        try {
            if (runner.scripts.contains(scriptName)) {
                throw new ScriptRecursionException("Скрипт " + scriptName + " уже выполняется.");
            }
            runner.scripts.add(scriptName);
            runner.setCurrentMode(RunningMode.SCRIPT);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(scriptName));
            Scanner oldScanner = runner.consoleManager.getScanner();
            Scanner newScanner = new Scanner(reader);
            ConsoleManager.setScanner(newScanner);
            while (runner.consoleManager.getScanner().hasNext()) {
                String[] currenrScriptCommand = ConsoleManager.readCommand();
                runner.launchCommand(currenrScriptCommand);
                if (!runner.getRunning()) {
                    return ExitCode.EXIT;
                }
            }
            ConsoleManager.setScanner(oldScanner);
            runner.scripts.remove(scriptName);
            runner.setCurrentMode(previousMode);
            return ExitCode.OK;
        } catch (ScriptRecursionException e) {
            ConsoleManager.printError(e.getMessage());
            return ExitCode.ERROR;
        } catch (IOException e) {
            ConsoleManager.printError("Невозможно прочитать скрипт из файла " + scriptName);
            runner.scripts.remove(scriptName);
            runner.setCurrentMode(previousMode);
            return ExitCode.ERROR;
        }
    }
}
