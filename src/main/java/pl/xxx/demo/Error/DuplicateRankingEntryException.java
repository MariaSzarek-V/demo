package pl.xxx.demo.Error;

import org.springframework.http.HttpStatus;


public class DuplicateRankingEntryException extends BusinessException {
    public DuplicateRankingEntryException() {
        super("Ranking dla danego meczu już istnieje", HttpStatus.CONFLICT);
    }
}