package lab6.utility.validators.musicband;

import lab6.entity.MusicBand;
import lab6.utility.validators.Validator;
import lab6.utility.validators.musicband.bestalbum.AlbumValidator;
import lab6.utility.validators.musicband.coordinates.CoordinatesValidator;

/**
 * Отвечает за правильность данных, введённных пользователем.
 * @author Alina
 */
public class MusicBandValidator implements Validator<MusicBand>{
    @Override
    public boolean validate(MusicBand musicBand) {
        return new IdValidator().validate(musicBand.getId())
            && new NameValidator().validate(musicBand.getName())
            && new CoordinatesValidator().validate(musicBand.getCoordinates())
            && new CreationDateValidator().validate(musicBand.getCreationDate())
            && new NumberOfParticipantsValidator().validate(musicBand.getNumberOfParticipants())
            && new AlbumValidator().validate(musicBand.getBestAlbum());
    }
}
