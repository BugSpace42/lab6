package lab5.managers;

import java.util.List;
import java.util.Scanner;

import lab5.utility.Console;

/**
 * Класс, отвечающий за взаимодействие с пользователем в консоли.
 * @author Alina
 */
public class ConsoleManager {
    private Console console;
    private Scanner scanner;
    private List<String> commands;

    /*
     * Я устанавливаю текущую консоль и сканер сразу же,
     * не дожидаясь первого использования менеджера, 
     * поскольку мне хочется, чтобы у него сразу же было
     * завершённое состояние. Если нужно будет использовать 
     * в работе другую консоль, то можно использовать сеттеры.
     */
    public ConsoleManager(Console console, Scanner scanner) {
        this.console = console;
        this.scanner = scanner;
    }

    // todo: greeting, writeException, readCommand, readObject
    // При readCommand передавать информацию о доступных командах и выдавать исключения

    /**
     * Приветствует пользователя и сообщает ему список команд.
     */
    public void greeting(){
        console.println("Добро пожаловать в приложение, созданное для управления коллекцией объектов класса MusicBand.");
        console.print("Доступны команды: ");
        
        /*
         * Прохожу в цикле, чтобы красиво вывести список команд.
         */
        for (int i = 0; i < commands.size()-1; i++) {
            console.print(commands.get(i) + ", ");
        }
        console.println(commands.getLast());
        
        console.println("Для более подробной информации о командах введите \"help\"");
    }

    /**
     * Считывает команду и проверяет её на корректность.
     * @return список, состоящий из названия команды и введённых аргументов
     */
    public String[] readCommand() {
        console.print("Введите команду: ");
        String [] text = scanner.nextLine().trim().split(" ");
        /*
        if (commands.contains(text[0])) {
            return text;
        }
        */
        return text;
    }


    /**
     * Заменяет информацию о консоли.
     * @param console новая консоль
     */
    public void setConsole(Console console) {
        this.console = console;
    }

    /**
     * Заменяет информацию о сканере.
     * @param scanner новый сканер
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Устанавливает списов команд, доступных в текущий момент.
     * @param commands множество названий команд
     */
    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    /**
     * Возвращает информацию о консоли.
     * @return текущая консоль
     */
    public Console getConsole() {
        return console;
    }

    /**
     * Возвращает информацию о сканере.
     * @return текущий сканер
     */
    public Scanner getScanner() {
        return scanner;
    }
}
