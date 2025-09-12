package pl.xxx.demo.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GameTimeStatusException extends RuntimeException {
    public GameTimeStatusException() {
        super("Data meczu niezgodna z jego statusem");
    }

}