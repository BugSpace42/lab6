package lab5.utility.validators;

import lab5.entity.MusicBand;

/**
 * Отвечает за правильность данных, введённных пользователем.
 * @author Alina
 */
public class MusicBandValidator implements Validator<MusicBand>{
    @Override
    public boolean validate(MusicBand musicBand) {
        return new MusicBandNameValidator().validate(musicBand.getName())
            && new CoordinatesValidator().validate(musicBand.getCoordinates())
            && new MusicBandNumberValidator().validate(musicBand.getNumberOfParticipants())
            && new AlbumValidator().validate(musicBand.getBestAlbum());
    }
}
