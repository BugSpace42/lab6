package lab5.utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lab5.entity.Album;
import lab5.entity.Coordinates;
import lab5.entity.MusicBand;
import lab5.entity.MusicGenre;
import lab5.exceptions.IdExistsException;
import lab5.exceptions.WrongValueException;
import lab5.managers.CollectionManager;
import lab5.managers.ConsoleManager;
import lab5.utility.builders.AlbumBuilder;
import lab5.utility.builders.CoordinatesBuilder;
import lab5.utility.validators.stringvalidators.KeyValidator;
import lab5.utility.validators.stringvalidators.musicband.CreationDateValidator;
import lab5.utility.validators.stringvalidators.musicband.IdValidator;
import lab5.utility.validators.stringvalidators.musicband.NameValidator;
import lab5.utility.validators.stringvalidators.musicband.NumberOfParticipantsValidator;
import lab5.utility.validators.stringvalidators.musicband.bestalbum.AlbumNameValidator;
import lab5.utility.validators.stringvalidators.musicband.coordinates.CoordXValidator;
import lab5.utility.validators.stringvalidators.musicband.coordinates.CoordYValidator;

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
        HashMap<Integer, MusicBand> collection = new HashMap<>();
        List<Long> idList = new ArrayList<>();

        if (fileLines.size() < 2) {
            return collection;
        }
        int numberOfColumns = fileLines.get(0).split(",").length;
        // пробегаемся с 1, т.к. строка 0 это заголовок
        for (int fileLineIndex = 1; fileLineIndex < fileLines.size(); fileLineIndex++) {
            try {
                String fileLine = fileLines.get(fileLineIndex);
                String[] splitedText = fileLine.split(",");
                LinkedList<String> columnList = new LinkedList<>();
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

                if (columnList.size() != numberOfColumns) {
                    // если количество столбцов в текцщей строке не равно правильному количеству, то 
                    // пропускаем эту строку или
                    // выкидываем исключение и после его обрабатываем
                    // continue;
                    throw new WrongValueException("В строке " + fileLineIndex +  " уканано неверное количество аргументов. Ожидалось: " + 
                    numberOfColumns + ". Получено: " + columnList.size());
                }

                Integer key;
                if (new KeyValidator().validate(getText(columnList.get(0)))) {
                    key = Integer.valueOf(getText(columnList.get(0)));
                }
                else {
                    throw new WrongValueException("В строке " + fileLineIndex +  " указан неверный ключ музыкальной группы.");
                }

                Long id;
                if (new IdValidator().validate(getText(columnList.get(1)))) {
                    id = Long.valueOf(getText(columnList.get(1)));
                }
                else {
                    throw new WrongValueException("В строке " + fileLineIndex +  " указан неверный id музыкальной группы.");
                }
                
                if (idList.contains(id)) {
                    throw new IdExistsException("Музыкальная группа с id " + id + " ужже существует.");
                }
                else {
                    idList.add(id);
                }

                String name;
                if (new NameValidator().validate(getText(columnList.get(2)))) {
                    name = getText(columnList.get(2));
                }
                else {
                    throw new WrongValueException("В строке " + fileLineIndex +  " указано неверное название музыкальной группы.");
                }

                Integer x;
                if (new CoordXValidator().validate(getText(columnList.get(3)))) {
                    x = Integer.valueOf(getText(columnList.get(3)));
                }
                else {
                    throw new WrongValueException("В строке " + fileLineIndex +  " указана неверная координата x музыкальной группы.");
                }

                long y;
                if (new CoordYValidator().validate(getText(columnList.get(4)))) {
                    y = Long.parseLong(getText(columnList.get(4)));
                }
                else {
                    throw new WrongValueException("В строке " + fileLineIndex +  " указана неверная координата x музыкальной группы.");
                }

                java.util.Date creationDate;
                if (new CreationDateValidator().validate(getText(columnList.get(5)))) {
                    creationDate = new Date(Long.parseLong(getText(columnList.get(5))));
                }
                else {
                    throw new WrongValueException("В строке " + fileLineIndex +  " указана неверная дата создания элемента класса MusicBand.");
                }

                Integer numberOfParticipants;
                if (new NumberOfParticipantsValidator().validate(getText(columnList.get(6)))) {
                    numberOfParticipants = Integer.valueOf(getText(columnList.get(6)));
                }
                else {
                    throw new WrongValueException("В строке " + fileLineIndex +  " указано неверное количество участников музыкальной группы.");
                }
                
                Coordinates coordinates = CoordinatesBuilder.build(x, y);
                MusicGenre genre;
                Album bestAlbum;
                if ("true".equals(getText(columnList.get(7)))) {
                    try {
                        genre = MusicGenre.valueOf(getText(columnList.get(8)));
                    } catch (IllegalArgumentException e) {
                        throw new WrongValueException("В строке " + fileLineIndex +  " указан неверный жанр музыкальной группы.");
                    }
                }
                else {
                    genre = null;
                }
                if ("true".equals(getText(columnList.get(9)))) {
                    String bestAlbumName;
                    if (new AlbumNameValidator().validate(getText(columnList.get(10)))) {
                        bestAlbumName = getText(columnList.get(10));
                    }
                    else {
                        throw new WrongValueException("В строке " + fileLineIndex +  " указано неверное название музыкального альбома.");
                    }
                    Double bestAlbumSales;
                    if (new AlbumNameValidator().validate(getText(columnList.get(11)))) {
                        bestAlbumSales = Double.valueOf(getText(columnList.get(11)));
                    }
                    else {
                        throw new WrongValueException("В строке " + fileLineIndex +  " указано неверное число продаж музыкального альбома.");
                    }
                    bestAlbum = AlbumBuilder.build(bestAlbumName, bestAlbumSales);
                }
                else {
                    bestAlbum = null;
                }
                MusicBand musicBand = new MusicBand(id, name, coordinates, creationDate, numberOfParticipants, genre, bestAlbum);
                collection.put(key, musicBand);
            } catch (WrongValueException | IdExistsException e) {
                ConsoleManager.printError(e.getMessage());
                ConsoleManager.println("Строка с ошибкой пропущена.");
            }
            
        }
        return collection;
    }

    /**
     * Переводит коллекцию объектов класса MusicBand в набор строк для файла формата csv.
     * @param collection коллекция объектов класса MusicBand
     * @return строки файла в формате csv
     */
    public static List<String> parseToCSV(HashMap<Integer, MusicBand> collection) {
        List<String> fileLines = new ArrayList<>();
        String title = "\"key\",\"id\",\"name\",\"x\",\"y\",\"creationDate\",\"numberOfParticipants\",\"isGenre\",\"genre\",\"isBestAlbum\",\"albumName\",\"albumSales\"";
        fileLines.add(title);
        for (Map.Entry<Integer, MusicBand> entry : collection.entrySet()) {
            Integer key = entry.getKey();
            MusicBand musicBand = entry.getValue();
            Long id = musicBand.getId();
            String name = musicBand.getName();
            Coordinates coordinates = musicBand.getCoordinates();
            Integer x = coordinates.getX();
            long y = coordinates.getY();
            java.util.Date creationDate = musicBand.getCreationDate();
            Integer numberOfParticipants = musicBand.getNumberOfParticipants();
            MusicGenre genre = musicBand.getGenre();
            Album bestAlbum = musicBand.getBestAlbum();

            LinkedList<String> columnList = new LinkedList<>();
            columnList.add(key.toString());
            columnList.add(id.toString());
            columnList.add(name);
            columnList.add(x.toString());
            columnList.add(String.valueOf(y));
            columnList.add(Long.toString(creationDate.getTime()));
            columnList.add(numberOfParticipants.toString());
            if (genre == null) {
                columnList.add("false");
                columnList.add("");
            }
            else {
                columnList.add("true");
                columnList.add(genre.toString());
            }
            if (bestAlbum == null) {
                columnList.add("false");
                columnList.add("");
                columnList.add("");
            }
            else {
                columnList.add("true");
                columnList.add(bestAlbum.getName());
                columnList.add(bestAlbum.getSales().toString());
            }

            StringBuilder line = new StringBuilder();
            for (String column : columnList) {
                line.append("\"");
                line.append(column);
                line.append("\"");
                line.append(",");
            }
            line.deleteCharAt(line.length()-1);
            fileLines.add(line.toString());
        }
        return fileLines;
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
