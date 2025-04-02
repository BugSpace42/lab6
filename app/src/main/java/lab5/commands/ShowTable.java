package lab5.commands;

import java.util.Map;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.u8.U8_Grids;
import lab5.entity.MusicBand;
import lab5.managers.CollectionManager;
import lab5.managers.ConsoleManager;
import lab5.utility.Command;
import lab5.utility.Runner;

/**
 * Выводит в стандартный поток вывода все элементы коллекции в виде таблицы
 * @author Alina
 */
public class ShowTable extends Command {
    public ShowTable(){
        super("show_table", "вывести все элементы коллекции в виде таблицы", 0);
    }

    /**
     * Выполняет команду.
     */
    @Override
    public Runner.ExitCode execute(String[] args) {
        CollectionManager collectionManager = CollectionManager.getCollectionManager();
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ключ", "id", "название", "x", "y", "дата создания", "дата создания", 
                    "кол-во участников", "жанр", "название альбома", "продажи альбома");
        table.addRule();
        for (Map.Entry<Integer, MusicBand> elem : collectionManager.getCollection().entrySet()) {
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

            table.addRow(elem.getKey(), musicBand.getId(), musicBand.getName(), musicBand.getCoordinates().getX(), musicBand.getCoordinates().getY(),
                        null, musicBand.getCreationDate(), musicBand.getNumberOfParticipants(), genre, albumName, albumSales);
            
            table.addRule();
        }
        table.getContext().setGrid(U8_Grids.borderDouble());
        String rend = table.render(120);
        ConsoleManager.println(rend);
        return Runner.ExitCode.OK;
    }
}
