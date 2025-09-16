package pl.xxx.demo.Error;

import org.springframework.http.HttpStatus;


public class GameDeleteNotAllowedException extends BusinessException {

    public GameDeleteNotAllowedException() {
        super("Można usunąć tylko mecz ze statusem ADMIN_VIEW", HttpStatus.BAD_REQUEST);
    }

}
