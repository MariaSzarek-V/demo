package pl.xxx.demo.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GameTimeStatusException extends BusinessException {
    public GameTimeStatusException() {
        super("Data meczu niezgodna z jego statusem", HttpStatus.BAD_REQUEST);
    }

}