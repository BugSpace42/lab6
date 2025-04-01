package lab5.entity;

import org.apache.commons.lang3.StringUtils;

import lab5.managers.ConsoleManager;
import lab5.utility.builders.MusicGenreBuilder;

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
     * Запрашивает у пользователя музыкальный жанр.
     * @return введённый музыкальный жанр.
     */
    public static MusicGenre askMusicGenre() {
        ConsoleManager.println("Введите музыкальный жанр группы (при наличии).");
        ConsoleManager.println("Список музыкальных жанров: " + MusicGenre.names());
        String genreString = ConsoleManager.askObject();
        MusicGenre genre;
        if (StringUtils.isBlank(genreString)) {
            genre = null;
        }
        else {
            try {
                genre = MusicGenreBuilder.build(genreString);
            } catch (IllegalArgumentException e) {
                ConsoleManager.println("Введён некорректный музыкальный жанр.");
                ConsoleManager.println("Попробуйте снова.");
                // запрашиваем у пользователя данные, пока не введёт подходящие
                genre = askMusicGenre();
            }
        }
        
        return genre;
    }

    /**
     * @return Строка со всеми элементами enum'а через запятую.
     */
    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (MusicGenre genre : values()) {
            nameList.append(genre.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}