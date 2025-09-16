package pl.xxx.demo.Error;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends BusinessException {
    public InvalidPasswordException() {
        super("Nieprawidłowe hasło", HttpStatus.BAD_REQUEST);
    }
}