package lab5.commands;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

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
        super("execute_script", "считать и исполнить скрипт из указанного файла");
        this.runner = runner;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public Runner.ExitCode execute(String[] args) {
        if (args.length < 2) {
            runner.consoleManager.printError("Не введено имя файла.");
            return ExitCode.ERROR;
        }
        String scriptName = args[1];
        for (int i = 2; i < args.length; i++) {
            scriptName += " " + args[i];
        }
        
        if (runner.scripts.contains(scriptName)) {
            runner.consoleManager.printError("Скрипт не может вызываться рекурсивно.");
            return ExitCode.ERROR;
        }
        runner.scripts.add(scriptName);
        RunningMode previousMode = runner.getCurrentMode();
        runner.setCurrentMode(RunningMode.SCRIPT);
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(scriptName));
            Scanner oldScanner = runner.consoleManager.getScanner();
            Scanner newScanner = new Scanner(reader);
            runner.consoleManager.setScanner(newScanner);
            while (runner.consoleManager.getScanner().hasNext()) {
                String[] currenrScriptCommand = runner.consoleManager.readCommand();
                runner.launchCommand(currenrScriptCommand);
                if (!runner.getRunning()) {
                    return ExitCode.EXIT;
                }
            }
            runner.consoleManager.setScanner(oldScanner);
        } catch (IOException e) {
            runner.consoleManager.printError("Невозможно прочитать скрипт из файла " + scriptName);
            runner.scripts.remove(scriptName);
            runner.setCurrentMode(previousMode);
            return ExitCode.ERROR;
        }
        runner.scripts.remove(scriptName);
        runner.setCurrentMode(previousMode);
        return ExitCode.OK;
    }
}
