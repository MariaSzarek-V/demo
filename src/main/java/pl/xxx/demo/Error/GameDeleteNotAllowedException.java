package pl.xxx.demo.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GameDeleteNotAllowedException extends RuntimeException {

    public GameDeleteNotAllowedException() {
        super("Można usunąć tylko mecz ze statusem ADMIN_VIEW");
    }

}
