package lab5.entity;

/**
 * Перечисление музыкальных жанров.
 * @author Alina
 */
public enum MusicGenre {
    ROCK,
    HIP_HOP,
    JAZZ,
    POP,
    PUNK_ROCK;

    /**
     * Возвращает строку с названием класса
     * @return строка с названием класса
     */
    public static String getClassName() {
        return "музыкальный жанр";
    }
}