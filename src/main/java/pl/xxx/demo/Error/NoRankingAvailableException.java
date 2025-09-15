package pl.xxx.demo.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class NoRankingAvailableException  extends BusinessException {
    public NoRankingAvailableException () {
        super("Ranking pojawi się po pierwszym meczu", HttpStatus.NOT_FOUND);
    }
}