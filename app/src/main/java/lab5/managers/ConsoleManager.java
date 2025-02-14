package lab5.managers;

import java.util.Scanner;

import lab5.entity.Album;
import lab5.entity.Coordinates;
import lab5.entity.MusicBand;
import lab5.entity.MusicGenre;
import lab5.utility.Console;
import lab5.utility.builders.AlbumBuilder;
import lab5.utility.builders.CoordinatesBuilder;
import lab5.utility.builders.MusicBandBuilder;

/**
 * Класс, отвечающий за взаимодействие с пользователем в консоли.
 * @author Alina
 */
public class ConsoleManager {
    private Console console;
    private Scanner scanner;

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
     * Выводит в консоль строковое представление объекта.
     * @param o объект, который нужно вывести
     */
    public void print(Object o) {
        console.print(o);
    }

    /**
     * Выводит в консоль строковое представление объекта с переносом строки.
     * @param o объект, который нужно вывести
     */
    public void println(Object o) {
        console.println(o);
    }

    /**
     * Выводит в консоль сообщение об ошибке.
     * @param o ошибка, которую нужно вывести
     */
    public void printError(Object o) {
        console.println(o);
    }

    /**
     * Приветствует пользователя и сообщает ему список команд.
     */
    public void greeting(){
        console.println("Добро пожаловать в приложение, созданное для управления коллекцией объектов класса MusicBand.");
        console.println("Для получения информации о доступных командах введите help");
    }

    /**
     * Запрашивает у пользователя и считывает команду.
     * @return список, состоящий из названия команды и введённых аргументов
     */
    public String[] askCommand() {
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
     * Запрашивает у пользователя и считывает объект.
     * @param objectName название объекта
     * @return строка, введённая пользователем
     */
    public String askObject(String objectName) {
        console.print("Введите " + objectName + ": ");
        String text = scanner.nextLine().trim();
        return text;
    }

    /**
     * Считывает команду.
     * @return список, состоящий из названия команды и аргументов
     */
    public String[] readCommand() {
        String [] text = scanner.nextLine().trim().split(" ");
        return text;
    }

    /**
     * Считывает объект.
     * @return считанная строка
     */
    public String readObject() {
        String text = scanner.nextLine().trim();
        return text;
    }

    /**
     * Запрашивает у пользователя объект класса MusicBand.
     * @return введённый объект класса MusicBand
     */
    public MusicBand askMusicBand() {
        String name; //Поле не может быть null, Строка не может быть пустой
        Coordinates coordinates; //Поле не может быть null
        Integer numberOfParticipants; //Поле не может быть null, Значение поля должно быть больше 0
        MusicGenre genre; //Поле может быть null
        Album bestAlbum; //Поле может быть null
        Integer x; //Поле не может быть null
        long y; //Значение поля должно быть больше -973
        String albumName; //Поле не может быть null, Строка не может быть пустой
        Double albumSales; //Поле может быть null, Значение поля должно быть больше 0

        name = askObject(MusicBand.getNameName());

        x = Integer.valueOf(askObject(Coordinates.getXName()));
        y = Long.parseLong(askObject(Coordinates.getYName()));
        coordinates = CoordinatesBuilder.build(x, y);

        numberOfParticipants = Integer.valueOf(askObject(MusicBand.getNumberOfParticipantsName()));

        genre = MusicGenre.valueOf(askObject(MusicGenre.getClassName()));

        albumName = askObject(Album.getNameName());
        albumSales = Double.valueOf(askObject(Album.getSalesName()));
        bestAlbum = AlbumBuilder.build(albumName, albumSales);
        MusicBand musicBand = MusicBandBuilder.build(name, coordinates, numberOfParticipants, genre, bestAlbum);
        return musicBand;
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
