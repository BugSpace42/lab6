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
        if (name.contains("\"")) {
            // строка содержит кавычки
            return false;
        }
        return true;
    }
}
