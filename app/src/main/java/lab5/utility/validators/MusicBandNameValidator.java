package lab5.utility.validators;

/**
 * Проверка корректности названия музыкальной группы.
 * @author Alina
 */
public class MusicBandNameValidator implements Validator<String>{
    @Override
    public boolean validate(String name) {
        if (name.isEmpty()) {
            // строка пустая или null
            return false;
        }
        return true;
    }
}
