package lab5.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    /**
     * Читает коллекцию объектов класса MusicBand из файла.
     * @return коллекция объектов
     * @throws IOException исключение, возникающее, если невозможно прочитать файл
     */
    /*
     * Объясню, почему я пробрасываю исключение, а не обрабатываю его здесь.
     * Этот метод отвечает лишь за то, чтобы читать из коллекции. Он не знает, что делать, если не получилось.
     * Взаимодействием с пользователем занимаются другие классы, в них и будет описана логика этих действий.
     * Чтение коллекции может быть реализовано в разных частях программы, и исключения при чтении файла там могут быть
     * реализованы по-разному. 
     * Пока что мне кажется, что будет правильно предупредить пользователя о том, что прочитать файл не удалось, 
     * и создать новую коллекцию. Однако можно и сказать пользователю, что мы не можем прочитать файл,
     * и попросить ввести другое имя файла. 
     * Аналогичная ситуация и с методом записи коллекции в файл.
     */
    public HashMap<Integer, MusicBand> readCollection() throws IOException {
        HashMap<Integer, MusicBand> collection;
        try (InputStreamReader collectionInputStreamReader = new InputStreamReader(new FileInputStream(this.collectionFileName))) {
            ArrayList<String> fileLines = readAllLines(collectionInputStreamReader);
            collection = ParserCSV.parseFromCSV(fileLines);
        }
        return collection;
    }

    /**
     * Читает все строки из потока.
     * @param inputStreamReader поток, из которого читаются строки
     * @return список строк
     * @throws IOException исключение, возникающее, если невозможно прочитать строку
     */
    public ArrayList<String> readAllLines(InputStreamReader inputStreamReader) throws IOException {
        ArrayList<String> lines;
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            lines = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        }
        return lines;
    }

    /**
     * Записывает в файл коллекцию объектов класса MusicBand.
     * @param collection коллекция объектов класса MusicBand
     * @throws IOException исключение, возникающее, если невозможно записать строку
     */
    /*
     * Причины, по которым я пробрасываю исключение, описаны в комментарии к readCollection().
     */
    public void writeCollection(HashMap<Integer, MusicBand> collection) throws IOException {
        List<String> fileLines = ParserCSV.parseToCSV(collection);
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(this.collectionFileName))) {
            writeAllLines(writer, fileLines);
            writer.flush();
        }
    }

    /**
     * Пишет в поток все переданные строки.
     * @param outputStreamWriter поток, в который пишутся строки
     * @param lines строки, которые нужно запиасать
     * @throws IOException исключение, возникающее, если невозможно записать строку
     */
    public void writeAllLines(OutputStreamWriter writer, List<String> lines) throws IOException {
        for (String line : lines) {
            writer.write(line + "\n");
        }
        writer.flush();
    }

    /**
     * Проверяет, существует ли файл с данным именем.
     * @param fileName имя файла
     * @return true - если файл существует, иначе false
     */
    public boolean isFileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
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
