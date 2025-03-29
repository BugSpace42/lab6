package lab5.utility.validators.stringvalidators.musicband;

import java.util.Date;

import lab5.utility.validators.Validator;

/**
 * Проверка корректности времени создания объекта класса MusicBand, лежащего в строке.
 * @author Alina
 */
public class CreationDateValidator implements Validator<String>{
    @Override
    public boolean validate(String creationDateString) {
        try {
            java.util.Date creationDate = new Date(Long.parseLong(creationDateString)); 
            Date currentDate = new Date();
            if (creationDate.after(currentDate)) {
                // значение поля должно быть меньше текущего времени
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
