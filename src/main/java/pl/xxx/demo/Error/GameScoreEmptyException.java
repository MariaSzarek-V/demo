package pl.xxx.demo.Error;

import org.springframework.http.HttpStatus;


public class GameScoreEmptyException extends BusinessException {
    public GameScoreEmptyException() {
        super("Wynik nie może być pusty", HttpStatus.BAD_REQUEST);
    }

}