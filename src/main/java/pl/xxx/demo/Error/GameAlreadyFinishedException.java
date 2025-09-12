package pl.xxx.demo.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GameAlreadyFinishedException extends RuntimeException {
    public GameAlreadyFinishedException(Long id) {
        super("Gra o id " + id + " jest zakończona i nie może zostać usunięta");
    }


}