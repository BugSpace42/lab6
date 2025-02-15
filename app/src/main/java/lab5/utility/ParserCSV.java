package lab5.utility;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import lab5.entity.Album;
import lab5.entity.Coordinates;
import lab5.entity.MusicBand;
import lab5.entity.MusicGenre;
import lab5.utility.builders.AlbumBuilder;
import lab5.utility.builders.CoordinatesBuilder;

/**
 * Преобразует коллекцию объектов класса MusicBand в файл формата csv, и наоборот. 
 * @author Alina
 */
public class ParserCSV {
    /**
     * Получает коллекцию объектов класса MusicBand из файла формата csv.
     * @param fileLines строки файла в формате csv
     * @return полученная коллекция
     */
    public static HashMap<Integer, MusicBand> parseFromCSV(List<String> fileLines) {
        HashMap<Integer, MusicBand> collection = new HashMap<Integer, MusicBand>();
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            LinkedList<String> columnList = new LinkedList<String>();
            for (String column : splitedText) {
                if (columnList.isEmpty()) {
                    columnList.add(column);
                    continue;
                }
                // Если последний добавленный столбец не полностью в кавычках, то добавляем к нему этот 
                String lastColumn = columnList.getLast();
                if (isColumnPart(lastColumn)) {
                    String newColumn = lastColumn + "," + column;
                    columnList.removeLast();
                    columnList.add(newColumn);
                    continue;
                }
                columnList.add(column);
            }

            Integer key = Integer.valueOf(getText(columnList.get(0)));
            Long id = Long.valueOf(getText(columnList.get(1)));
            String name = getText(columnList.get(2));
            Integer x = Integer.valueOf(getText(columnList.get(3)));
            long y = Long.parseLong(getText(columnList.get(4)));
            Coordinates coordinates = CoordinatesBuilder.build(x, y);
            java.util.Date creationDate = new Date(Long.parseLong(getText(columnList.get(5)))); 
            Integer numberOfParticipants = Integer.valueOf(getText(columnList.get(6)));
            MusicGenre genre;
            Album bestAlbum;
            if ("\"true\"".equals(getText(columnList.get(7)))) {
                genre = MusicGenre.valueOf(getText(columnList.get(8)));
            }
            else {
                genre = null;
            }
            if ("\"true\"".equals(getText(columnList.get(9)))) {
                String bestAlbumName = getText(columnList.get(10));
                Double bestAlbumSales = Double.valueOf(getText(columnList.get(11)));
                bestAlbum = AlbumBuilder.build(bestAlbumName, bestAlbumSales);
            }
            else {
                bestAlbum = null;
            }
            MusicBand musicBand = new MusicBand(id, name, coordinates, creationDate, numberOfParticipants, genre, bestAlbum);
            collection.put(key, musicBand);
        }
        return collection;
    }

    /**
     * Проверяет, является текст частью другого столбца
     * @param text текст текущего столбца
     * @return true - если текст часть другого столбца, false - если нет 
     */
    private static boolean isColumnPart(String text) {
        String trimText = text.trim();
        // Если в начале и в конце строки стоят кавычки, значит это целый столбец
        // Иначе, это часть предыдущего или следующего столбца
        return !(trimText.startsWith("\"") && trimText.endsWith("\""));
    }

    /**
     * Возвращает текст столбца (обрезает кавычки).
     * @param column столбец
     * @return текст, который содержит столбец
     */
    private static String getText(String column) {
        return column.substring(1, column.length()-1);
    }
}
