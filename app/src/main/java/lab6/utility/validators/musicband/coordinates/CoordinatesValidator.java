package lab6.utility.validators.musicband.coordinates;

import lab6.entity.Coordinates;
import lab6.utility.validators.Validator;

/**
 * Проверка корректности координат.
 * @author Alina
 */
public class CoordinatesValidator implements Validator<Coordinates>{
    @Override
    public boolean validate(Coordinates coord) {
        return new CoordXValidator().validate(coord.getX()) 
            && new CoordYValidator().validate(coord.getY());
    }
}
