package pl.xxx.demo.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoRankingAvailableException  extends RuntimeException {
    public NoRankingAvailableException () {
        super("Ranking pojawi się po pierwszym meczu");
    }

}