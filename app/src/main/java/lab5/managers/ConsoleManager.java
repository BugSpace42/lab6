package lab5.managers;

import java.util.NoSuchElementException;
import java.util.Scanner;

import lab5.exceptions.CanceledCommandException;
import lab5.utility.Console;
import lab5.utility.StandardConsole;

/**
 * Класс, отвечающий за взаимодействие с пользователем в консоли.
 * @author Alina
 */
public class ConsoleManager {
    private static ConsoleManager consoleManager;
    private static Console console = new StandardConsole();
    private static Scanner scanner = new Scanner(System.in, "Cp866");

    private ConsoleManager() {}

    /**
     * Метод, использующийся для получения ConsoleManager.
     * Создаёт новый объект, если текущий объект ещё не создан.
     * @return consoleManager
     */
    public static ConsoleManager getConsoleManager() {
        if (consoleManager == null) {
            consoleManager = new ConsoleManager();
        }
        return consoleManager;
    }

    /**
     * Выводит в консоль строковое представление объекта.
     * @param o объект, который нужно вывести
     */
    public static void print(Object o) {
        console.print(o);
    }

    /**
     * Выводит в консоль строковое представление объекта с переносом строки.
     * @param o объект, который нужно вывести
     */
    public static void println(Object o) {
        console.println(o);
    }

    /**
     * Выводит в консоль сообщение об ошибке.
     * @param o ошибка, которую нужно вывести
     */
    public static void printError(Object o) {
        console.println(o);
    }

    /**
     * Приветствует пользователя и сообщает ему список команд.
     */
    public static void greeting(){
        console.println("Добро пожаловать в приложение, созданное для управления коллекцией объектов класса MusicBand.");
        console.println("Для получения информации о доступных командах введите help");
    }

    /**
     * Запрашивает у пользователя и считывает команду.
     * @return список, состоящий из названия команды и введённых аргументов
          * @throws CanceledCommandException 
          */
         public static String[] askCommand() throws CanceledCommandException {
        console.println("Введите команду: ");
        console.print("> ");
        try {
            String [] text = scanner.nextLine().trim().split(" ");
            return text;
        } catch (NoSuchElementException e) {
            throw new CanceledCommandException("Обнаружен конец потока.");
        }
    }

    /**
     * Запрашивает у пользователя и считывает объект.
     * @param objectName название объекта
     * @return строка, введённая пользователем
          * @throws CanceledCommandException 
          */
         public static String askObject() throws CanceledCommandException {
        console.print("> ");
        try {
            String text = scanner.nextLine().trim();
            return text;
        } catch (NoSuchElementException e) {
            throw new CanceledCommandException("Обнаружен конец потока.");
        }
    }

    /**
     * Считывает команду.
     * @return список, состоящий из названия команды и аргументов
     */
         public static String[] readCommand() {
        try {
            String [] text = scanner.nextLine().trim().split(" ");
            return text;
        } catch (NoSuchElementException e) {
            println("Обнаружен конец потока.");
            return null;
        }
    }

    /**
     * Считывает объект.
     * @return считанная строка
     */
         public static String readObject() {
        try {
            String text = scanner.nextLine().trim();
            return text;
        } catch (NoSuchElementException e) {
            println("Обнаружен конец потока.");
            return null;
        }
    }

    /**
     * Заменяет информацию о консоли.
     * @param console новая консоль
     */
    public static void setConsole(Console console) {
        ConsoleManager.console = console;
    }

    /**
     * Заменяет информацию о сканере.
     * @param scanner новый сканер
     */
    public static void setScanner(Scanner scanner) {
        ConsoleManager.scanner = scanner;
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
