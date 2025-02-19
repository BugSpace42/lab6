package lab5.utility.validators;

/**
 * Проверка корректности количества участников музыкальной группы.
 * @author Alina
 */
public class MusicBandNumberValidator  implements Validator<Integer>{
    @Override
    public boolean validate(Integer numberOfParticipants) {
        if (numberOfParticipants <= 0) {
            // значение поля должно быть больше 0
            return false;
        }
        return true;
    }
}
