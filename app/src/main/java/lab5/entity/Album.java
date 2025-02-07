package lab5.entity;

/**
 * Класс альбома.
 * @author Alina
 */
public class Album {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Double sales; //Поле может быть null, Значение поля должно быть больше 0

    /**
     * Конструктор - создание нового объекта с заданными именем и продажами.
     * @param name
     * @param sales
     */
    public Album(String name, Double sales) {
        this.name = name;
        this.sales = sales;
    }

    /**
     * @return Строковое представление объекта класса.
     */
    public String toString() {
        return String.format("Название: %s \n Объём продаж: %.2f", name, sales);
    }
}