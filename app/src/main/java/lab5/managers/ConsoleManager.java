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
import lab5.utility.builders.MusicGenreBuilder;
import lab5.utility.validators.AlbumNameValidator;
import lab5.utility.validators.AlbumSalesValidator;
import lab5.utility.validators.CoordXValidator;
import lab5.utility.validators.CoordYValidator;
import lab5.utility.validators.MusicBandNameValidator;
import lab5.utility.validators.MusicBandNumberValidator;

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
        if (scanner.hasNext()) {
            String [] text = scanner.nextLine().trim().split(" ");
            return text;
        }
        else {
            println("Обнаружен конец потока.");
            return null;
        }
    }

    /**
     * Запрашивает у пользователя и считывает объект.
     * @param objectName название объекта
     * @return строка, введённая пользователем
     */
    public String askObject(String objectName) {
        console.print("Введите " + objectName + ": ");
        if (scanner.hasNext()) {
            String text = scanner.nextLine().trim();
            return text;
        }
        else {
            println("Обнаружен конец потока.");
            return null;
        }
    }

    /**
     * Считывает команду.
     * @return список, состоящий из названия команды и аргументов
     */
    public String[] readCommand() {
        if (scanner.hasNext()) {
            String [] text = scanner.nextLine().trim().split(" ");
            return text;
        }
        else {
            println("Обнаружен конец потока.");
            return null;
        }
    }

    /**
     * Считывает объект.
     * @return считанная строка
     */
    public String readObject() {
        if (scanner.hasNext()) {
            String text = scanner.nextLine().trim();
            return text;
        }
        else {
            println("Обнаружен конец потока.");
            return null;
        }
    }

    /**
     * Запрашивает у пользователя название музыкальной группы.
     * @return введённое название музыкальной группы.
     */
    public String askMusicBandName() {
        String name = askObject(MusicBand.getNameName());
        MusicBandNameValidator validator = new MusicBandNameValidator();
        if (validator.validate(name)) {
            return name;
        }
        else {
            console.println("Введено некорректное название музыкальной группы.");
            console.println("Название музыкальной группы не должно быть пустым и не должно содержать кавычки.");
            console.println("Попробуйте снова.");
            // запрашиваем у пользователя данные, пока не введёт подходящие
            name = askMusicBandName();
        }
        return name;
    }

    /**
     * Запрашивает у пользователя координату x.
     * @return введённая координата.
     */
    public Integer askCoordX() {
        String xString = askObject(Coordinates.getXName());
        Integer x;
        CoordXValidator validator = new CoordXValidator();
        try {
            x = Integer.valueOf(xString);
        } catch (NumberFormatException e) {
            console.println("Введённая строка не является числом типа Integer.");
            console.println("Попробуйте снова.");
            x = askCoordX();
        }
        if (validator.validate(x)) {
            return x;
        }
        else {
            console.println("Введена некорректная координата.");
            console.println("Попробуйте снова.");
            // запрашиваем у пользователя данные, пока не введёт подходящие
            x = askCoordX();
        }
        return x;
    }

    /**
     * Запрашивает у пользователя координату y.
     * @return введённая координата.
     */
    public long askCoordY() {
        String yString = askObject(Coordinates.getYName());
        long y;
        CoordYValidator validator = new CoordYValidator();
        try {
            y = Long.parseLong(yString);
        } catch (NumberFormatException e) {
            console.println("Введённая строка не является числом типа long.");
            console.println("Попробуйте снова.");
            y = askCoordY();
        }
        if (validator.validate(y)) {
            return y;
        }
        else {
            console.println("Введена некорректная координата.");
            console.println("Попробуйте снова.");
            // запрашиваем у пользователя данные, пока не введёт подходящие
            y = askCoordY();
        }
        return y;
    }

    /**
     * Запрашивает у пользователя количество участников музыкальной группы.
     * @return введённое количество участников музыкальной группы.
     */
    public Integer askMusicBandNumber() {
        Integer numberOfParticipants;
        String numberSrting = askObject(MusicBand.getNumberOfParticipantsName());
        MusicBandNumberValidator validator = new MusicBandNumberValidator();
        try {
            numberOfParticipants = Integer.valueOf(numberSrting);
        } catch (NumberFormatException e) {
            console.println("Введённая строка не является числом типа Integer.");
            console.println("Попробуйте снова.");
            numberOfParticipants = askMusicBandNumber();
        }
        if (validator.validate(numberOfParticipants)) {
            return numberOfParticipants;
        }
        else {
            console.println("Введено некорректное количество участников музыкальной группы.");
            console.println("Попробуйте снова.");
            // запрашиваем у пользователя данные, пока не введёт подходящие
            numberOfParticipants = askMusicBandNumber();
        }
        return numberOfParticipants;
    }

    /**
     * Запрашивает у пользователя музыкальный жанр.
     * @return введённый музыкальный жанр.
     */
    public MusicGenre askMusicGenre() {
        console.print("Есть ли у группы музыкальный жанр? (y/n) ");
        String answer = readObject();
        switch (answer) {
            case "y" -> {}
            default -> {
                // нет музыкального жанра
                return null;
            }
        }
        console.println("Список музыкальных жанров: " + MusicGenre.names());
        String genreString = askObject(MusicGenre.getClassName());
        MusicGenre genre;
        try {
            genre = MusicGenreBuilder.build(genreString);
        } catch (IllegalArgumentException e) {
            console.println("Введён некорректный музыкальный жанр.");
            console.println("Попробуйте снова.");
            // запрашиваем у пользователя данные, пока не введёт подходящие
            genre = askMusicGenre();
        }
        return genre;
    }

    /**
     * Запрашивает у пользователя название музыкального альбома.
     * @return введённое название музыкального альбома.
     */
    public String askAlbumName() {
        String name = askObject(Album.getNameName());
        AlbumNameValidator validator = new AlbumNameValidator();
        if (validator.validate(name)) {
            return name;
        }
        else {
            console.println("Введено некорректное название музыкального альбома.");
            console.println("Название музыкального альбома не должно быть пустым и не должно содержать кавычки.");
            console.println("Попробуйте снова.");
            // запрашиваем у пользователя данные, пока не введёт подходящие
            name = askMusicBandName();
        }
        return name;
    }

    /**
     * Запрашивает у пользователя продажи музыкального альбома.
     * @return введённые продажи музыкального альбома.
     */
    public Double askAlbumSales() {
        String salesString = askObject(Album.getSalesName());
        Double sales;
        AlbumSalesValidator validator = new AlbumSalesValidator();
        try {
            sales = Double.valueOf(salesString);
        } catch (NumberFormatException e) {
            console.println("Введённая строка не является числом типа Double.");
            console.println("Попробуйте снова.");
            sales = askAlbumSales();
        }
        if (validator.validate(sales)) {
            return sales;
        }
        else {
            console.println("Введено некорректные продажи музыкального альбома.");
            console.println("Попробуйте снова.");
            // запрашиваем у пользователя данные, пока не введёт подходящие
            sales = askAlbumSales();
        }
        return sales;
    }

    /**
     * Запрашивает у пользователя объект класса Coordinates.
     * @return введённый объект класса Coordinates
     */
    public Coordinates askCoordinates() {
        Integer x = askCoordX();
        long y = askCoordY();
        Coordinates coordinates = CoordinatesBuilder.build(x, y);
        return coordinates;
    }

    /**
     * Запрашивает у пользователя объект класса Album.
     * @return введённый объект класса Album
     */
    public Album askAlbum() {
        console.print("Есть ли у группы лучший альбом? (y/n) ");
        String answer = readObject();
        switch (answer) {
            case "y" -> {}
            default -> {
                // нет лучшего альбома
                return null;
            }
        }
        String name = askAlbumName();
        Double sales = askAlbumSales();
        Album album = AlbumBuilder.build(name, sales);
        return album;
    }

    /**
     * Запрашивает у пользователя объект класса MusicBand.
     * @return введённый объект класса MusicBand
     */
    public MusicBand askMusicBand() {
        String name = askAlbumName();
        Coordinates coordinates = askCoordinates();
        Integer numberOfParticipants = askMusicBandNumber();
        MusicGenre genre = askMusicGenre();
        Album bestAlbum = askAlbum();

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
