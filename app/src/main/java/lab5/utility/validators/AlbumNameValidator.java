package lab5.utility.validators;

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
        return true;
    }
}
