package lab6.utility.builders;

import lab6.entity.MusicGenre;

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
