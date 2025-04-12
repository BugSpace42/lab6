package lab6.utility.validators.musicband;

import lab6.utility.validators.Validator;

/**
 * Проверка корректности количества участников музыкальной группы.
 * @author Alina
 */
public class NumberOfParticipantsValidator  implements Validator<Integer>{
    @Override
    public boolean validate(Integer numberOfParticipants) {
        if (numberOfParticipants <= 0) {
            // значение поля должно быть больше 0
            return false;
        }
        return true;
    }
}
