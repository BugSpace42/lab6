package lab5.utility.builders;

import java.util.Date;
import java.util.List;

import lab5.entity.Album;
import lab5.entity.Coordinates;
import lab5.entity.MusicBand;
import lab5.entity.MusicGenre;
import lab5.managers.CollectionManager;

/**
 * Управляет сборкой объекта класса MusicBand 
 * @author Alina
 */
public class MusicBandBuilder {
    private static Long currentId = Long.valueOf(1);

    /**
     * Увеличивает счётчик id музыкальных групп на 1.
     * @return текущее id
     */
    private static Long nextId() {
        return currentId++;
    }

    /**
     * Собирает объект класса MusicBand.
     * @param name название музыкальной группы
     * @param coordinates местоположение музыкальной группы
     * @param numberOfParticipants количество участников музыкальной группы
     * @param genre жанр музыкальной группы
     * @param bestAlbum лучший альбом музыкальной группы
     * @return объект класса MusicBand
     */
    public static MusicBand build(String name, Coordinates coordinates, Integer numberOfParticipants, MusicGenre genre, Album bestAlbum) {
        Long id;
        id = nextId();
        List<Long> idList = CollectionManager.getIdList();
        while(idList.contains(id)) {
            id = nextId();
        }
        Date date = new Date();
        MusicBand musicBand = new MusicBand(id, name, coordinates, date, numberOfParticipants, genre, bestAlbum);
        return musicBand;
    }
}
