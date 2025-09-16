package pl.xxx.demo.Error;

import org.springframework.http.HttpStatus;

public class GameTimeStatusException extends BusinessException {
    public GameTimeStatusException() {
        super("Data meczu niezgodna z jego statusem", HttpStatus.BAD_REQUEST);
    }

}