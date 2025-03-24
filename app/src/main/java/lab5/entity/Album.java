package lab5.entity;

import lab5.managers.ConsoleManager;
import lab5.utility.builders.AlbumBuilder;
import lab5.utility.validators.musicband.bestalbum.AlbumNameValidator;
import lab5.utility.validators.musicband.bestalbum.AlbumSalesValidator;

/**
 * Класс альбома.
 * @author Alina
 */
public class Album implements Comparable<Album>{
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Double sales; //Поле может быть null, Значение поля должно быть больше 0

    /**
     * Конструктор - создание нового объекта с заданными именем и продажами.
     * @param name название альбома
     * @param sales продажи альбома
     */
    public Album(String name, Double sales) {
        this.name = name;
        this.sales = sales;
    }

    
    /**
     * Запрашивает у пользователя название музыкального альбома.
     * @return введённое название музыкального альбома.
     */
    public static String askAlbumName() {
        ConsoleManager.println("Введите название музыкального альбома.");
        ConsoleManager.println("Название музыкального альбома не должно быть пустым и не должно содержать кавычки.");
        String name = ConsoleManager.askObject();
        AlbumNameValidator validator = new AlbumNameValidator();
        if (validator.validate(name)) {
            return name;
        }
        else {
            ConsoleManager.println("Введено некорректное название музыкального альбома.");
            ConsoleManager.println("Название музыкального альбома не должно быть пустым и не должно содержать кавычки.");
            ConsoleManager.println("Попробуйте снова.");
            // запрашиваем у пользователя данные, пока не введёт подходящие
            name = askAlbumName();
        }
        return name;
    }

    /**
     * Запрашивает у пользователя продажи музыкального альбома.
     * @return введённые продажи музыкального альбома.
     */
    public static Double askAlbumSales() {
        ConsoleManager.println("Введите число продаж музыкального альбома.");
        ConsoleManager.println("Число продаж должно быть числом типа Double, большим чем 0.");
        String salesString = ConsoleManager.askObject();
        Double sales;
        AlbumSalesValidator validator = new AlbumSalesValidator();
        try {
            sales = Double.valueOf(salesString);
        } catch (NumberFormatException e) {
            ConsoleManager.println("Введённая строка не является числом типа Double.");
            ConsoleManager.println("Попробуйте снова.");
            sales = askAlbumSales();
        }
        if (validator.validate(sales)) {
            return sales;
        }
        else {
            ConsoleManager.println("Введено некорректные продажи музыкального альбома.");
            ConsoleManager.println("Попробуйте снова.");
            // запрашиваем у пользователя данные, пока не введёт подходящие
            sales = askAlbumSales();
        }
        return sales;
    }

    /**
     * Запрашивает у пользователя объект класса Album.
     * @return введённый объект класса Album
     */
    public static Album askAlbum() {
        ConsoleManager.print("Есть ли у группы лучший альбом? (y/n) ");
        String answer = ConsoleManager.readObject();
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

    public String getName() {
        return name;
    }

    public Double getSales(){
        return sales;
    }

    /**
     * Проверка равенства двух музыкальных альбомов.
     * @param album альбом для сравнения
     * @return true - если объекты одинаковые, false - если разные
     */
    public boolean equals(Album album) {
        if (album == this) return true;
        return album.name.equals(this.name) && album.sales.equals(this.sales);
    }

    @Override
    public int compareTo(Album album) {
        return this.name.compareTo(album.name);
    }

    /**
     * @return Строковое представление объекта класса.
     */
    @Override
    public String toString() {
        return String.format("Название: %s \n Объём продаж: %.2f", name, sales);
    }
}