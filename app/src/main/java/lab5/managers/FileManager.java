package lab5.managers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

import lab5.entity.MusicBand;
import lab5.utility.ParserCSV;

// написать методы openFile, closeFile, readCollection, writeCollection

/**
 * Класс, отвечающий за взаимодействие с файлами.
 * @author Alina
 */
public class FileManager {
    private String collectionFileName;

    /**
     * Конструктор
     * @param collectionFileName имя файла, из которого читается и в который сохраняется коллекция
     */
    public FileManager(String collectionFileName) {
        this.collectionFileName = collectionFileName;
    }

    // TODO writeCollection
    public void writeCollection(HashMap<Integer, MusicBand> collection) {
        //OutputStreamWriter collectionOutputStreamWriter = new OutputStreamWriter(new FileOutputStream(this.collectionFileName));
    }

    public HashMap<Integer, MusicBand> readCollection() {
        try (InputStreamReader collectionInputStreamReader = new InputStreamReader(new FileInputStream(this.collectionFileName))) {
            ArrayList<String> fileLines = readAllLines(collectionInputStreamReader);
            HashMap<Integer, MusicBand> collection = ParserCSV.parseFromCSV(fileLines);
            return collection;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> readAllLines(InputStreamReader inputStreamReader) {
        BufferedReader reader = new BufferedReader(inputStreamReader);
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        try {
            line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Устанавливает новое имя файла коллекции
     * @param newCollectionFileName новое имя файла коллекции
     */
    public void setCollectionFileName(String newCollectionFileName) {
        this.collectionFileName = newCollectionFileName;
    }

    /**
     * Возвращает текущее имя файла коллекции
     * @return текущее имя файла коллекции
     */
    public String getCollectionFileName() {
        return collectionFileName;
    }
}
