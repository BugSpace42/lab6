package lab6.utility.validators.musicband.coordinates;

import lab6.utility.validators.Validator;

/**
 * Проверка корректности координаты x.
 * @author Alina
 */
public class CoordXValidator implements Validator<Integer>{
    @Override
    public boolean validate(Integer x) {
        if (x == null) {
            // координата не задана
            return false;
        }
        return true;
    }
}
