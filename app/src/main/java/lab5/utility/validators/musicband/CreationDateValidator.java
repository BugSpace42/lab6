package lab5.utility.validators.musicband;

import java.util.Date;

import lab5.utility.validators.Validator;

/**
 * Проверка корректности времени создания объекта класса MusicBand.
 * @author Alina
 */
public class CreationDateValidator implements Validator<Date>{
    @Override
    public boolean validate(Date creationDate) {
        Date currentDate = new Date();
        if (creationDate.after(currentDate)) {
            // значение поля должно быть меньше текущего времени
            return false;
        }
        return true;
    }
}
