package pl.xxx.demo.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class PredictionEditNotAllowedException extends BusinessException {
    public PredictionEditNotAllowedException() {
        super("Typowanie możliwe tylko dla meczów, które jeszcze się nie rozpoczęły", HttpStatus.CONFLICT);
    }
}