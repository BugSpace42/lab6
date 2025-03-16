package lab5.commands;

import java.util.Map;

import de.vandermeer.asciitable.AsciiTable;
import lab5.entity.MusicBand;
import lab5.managers.ConsoleManager;
import lab5.utility.Command;
import lab5.utility.Runner;

/**
 * Выводит в стандартный поток вывода все элементы коллекции в виде таблицы
 * @author Alina
 */
public class ShowTable extends Command {
    private final Runner runner;

    public ShowTable(Runner runner){
        super("show_table", "вывести все элементы коллекции в виде таблицы", 0);
        this.runner = runner;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public Runner.ExitCode execute(String[] args) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("id", "название", "x", "y", "дата создания", "кол-во участников", "жанр", "название альбома", "продажи альбома");
        table.addRule();
        for (Map.Entry<Integer, MusicBand> elem : runner.collectionManager.getCollection().entrySet()) {
            MusicBand musicBand = elem.getValue();

            String genre;
            if (musicBand.getGenre() == null) {
                genre = "-";
            }
            else {
                genre = musicBand.getGenre().toString();
            }

            String albumName;
            String albumSales;
            if (musicBand.getBestAlbum() == null) {
                albumName = "-";
                albumSales = "-";
            }
            else {
                albumName = musicBand.getBestAlbum().getName();
                albumSales = musicBand.getBestAlbum().getSales().toString();
            }

            table.addRow(musicBand.getId(), musicBand.getName(), musicBand.getCoordinates().getX(), musicBand.getCoordinates().getY(),
                        musicBand.getCreationDate(), musicBand.getNumberOfParticipants(), genre, albumName, albumSales);
            
            table.addRule();
        }
        String rend = table.render();
        ConsoleManager.println(rend);
        return Runner.ExitCode.OK;
    }
}
