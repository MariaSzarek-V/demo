package pl.xxx.demo.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.CONFLICT)
public class PredictionAlreadyExistForGameException extends RuntimeException {
    public PredictionAlreadyExistForGameException() {
        super("Typowany wynik dla danego meczu już istnieje.");
    }

}