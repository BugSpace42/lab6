package lab5.entity;

/**
 * Класс координат.
 * @author Alina
 */
public class Coordinates {
    private Integer x; //Поле не может быть null
    private long y; //Значение поля должно быть больше -973

    /**
     * Конструктор - создание нового объекта с заданными x и y.
     * @param x - первая координата
     * @param y - вторая координата
     */
    public Coordinates(Integer x, long y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * @return Строковое представление объекта класса.
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}