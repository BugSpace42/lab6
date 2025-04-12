package lab6.utility.validators.musicband.bestalbum;

import lab6.utility.validators.Validator;

/**
 * Проверка корректности названия альбома.
 * @author Alina
 */
public class AlbumNameValidator implements Validator<String>{
    @Override
    public boolean validate(String name) {
        if (name.isEmpty()) {
            // строка пустая или null
            return false;
        }
        if (name.contains("\"")) {
            // строка содержит кавычки
            return false;
        }
        return true;
    }
}
