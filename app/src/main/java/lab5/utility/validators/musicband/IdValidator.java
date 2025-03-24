package lab5.utility.validators.musicband;

/**
 * Проверка корректности ID музыкальной группы.
 * @author Alina
 */
public class IdValidator {
    public boolean validate(Long id) {
        if (id <= 0) {
            // значение поля должно быть больше 0
            return false;
        }
        return true;
    }
}
