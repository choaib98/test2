package ma.wafaimmobilier.sav.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException  {
    private static final long serialVersionUID = 5675770841307011222L;

    public BadRequestException(String message) {
        super(message);
    }


    public BadRequestException(Long id, Long objectId) {
        super("Impossible de mettre à jour l'entité, l'id ressource "+id+ " ne correspond pas à l'id de l'objet "+objectId);
    }


}
