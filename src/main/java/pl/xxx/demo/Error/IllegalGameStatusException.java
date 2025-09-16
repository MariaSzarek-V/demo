package pl.xxx.demo.Error;

import org.springframework.http.HttpStatus;

public class IllegalGameStatusException extends BusinessException {
    public IllegalGameStatusException() {
        super("Nieprawidłowy status gry. Dopuszczalne: FINISHED, ADMIN_VIEW, SCHEDULED.", HttpStatus.BAD_REQUEST);
    }

}