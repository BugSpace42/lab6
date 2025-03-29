package lab5.utility.validators.stringvalidators.musicband;

import lab5.utility.validators.Validator;

/**
 * Проверка корректности ID музыкальной группы, лежащего в строке.
 * @author Alina
 */
public class IdValidator implements Validator<String>{
    @Override
    public boolean validate(String idString) {
        try {
            Long id = Long.valueOf(idString);
            if (id <= 0) {
                // значение поля должно быть больше 0
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
