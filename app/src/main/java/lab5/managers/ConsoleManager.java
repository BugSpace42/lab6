package lab5.managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private static Scanner scanner;
    private static BufferedReader reader;

    private ConsoleManager() {
        updateReader();
        updateScanner();
    }

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
     * Приветствует пользователя.
     */
    public static void greeting(){
        console.println("Добро пожаловать в приложение, созданное для управления коллекцией объектов класса MusicBand.");
        console.println("Для получения информации о доступных командах введите help");
    }

    /**
     * Запрашивает у пользователя и считывает команду.
     * @return список, состоящий из названия команды и введённых аргументов
     */
    public static String[] askCommand() {
        try (BufferedReader newReader = new BufferedReader(new InputStreamReader(System.in))){
            console.println("Введите команду: ");
            console.print("> ");
            println("Пытаюсь прочитать строку из потока.");
            String line = newReader.readLine();
            println("Получилось прочитать строку из потока.");
            if (line == null) {
                ConsoleManager.println("line == null");
                updateReader();
                return null;
            }
            String [] text = line.trim().split(" ");
            println("Получилось разделить строку на части.");
            return text;
        } catch (NullPointerException e) {
            println("Обнаружен конец потока.");
            return null;
        } catch (IOException e) {
            println("Не получилось прочитать строку из потока.");
            return null;
        }
        //catch (Exception e) {
        //    println("Какая-то ошибка");
        //    return null;
        //}
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
            String text = scanner.nextLine();
            if (text == null) {
                ConsoleManager.println("text == null");
                text = askObject();
            }
            else {
                text = text.trim();
            }
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
     * Создаёт новый сканер.
     */
    private static void updateScanner() {
        ConsoleManager.scanner = new Scanner(System.in, "Cp866");
    }

    /**
     * Создаёт новый ридер.
     */
    private static void updateReader() {
        if (ConsoleManager.reader != null) {
            try {
                ConsoleManager.reader.close();
                println("Старый поток закрыт.");
            } catch (IOException e) {
                println("Не удаётся закрыть поток.");
            }
        }
        ConsoleManager.reader = new BufferedReader(new InputStreamReader(System.in));
        println("Создан новый поток.");
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
