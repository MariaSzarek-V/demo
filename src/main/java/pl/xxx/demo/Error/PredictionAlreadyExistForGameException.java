package pl.xxx.demo.Error;

import org.springframework.http.HttpStatus;

public class PredictionAlreadyExistForGameException extends BusinessException {
    public PredictionAlreadyExistForGameException() {
        super("Typowany wynik dla danego meczu już istnieje.", HttpStatus.CONFLICT);
    }
}