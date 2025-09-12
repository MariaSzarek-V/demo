package pl.xxx.demo.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class PredictionEditNotAllowedException extends RuntimeException {
    public PredictionEditNotAllowedException() {
        super("Typowanie możliwe tylko dla meczów, które jeszcze się nie rozpoczęły");
    }

}