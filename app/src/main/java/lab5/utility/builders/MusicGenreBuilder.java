package lab5.utility.builders;

import lab5.entity.MusicGenre;

/**
 * Управляет сборкой объекта класса MusicGenre 
 * @author Alina
 */
public class MusicGenreBuilder {
    /**
     * Собирает объект класса MusicGenre
     * @param genre музыкальный жанр
     * @return объект класса MusicGenre
     */
    public static MusicGenre build(String genre) {
        genre = genre.trim().toUpperCase();
        MusicGenre musicGenre = MusicGenre.valueOf(genre);
        return musicGenre;
    }
}
