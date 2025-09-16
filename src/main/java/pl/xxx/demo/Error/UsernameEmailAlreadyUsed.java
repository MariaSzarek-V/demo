package pl.xxx.demo.Error;

import org.springframework.http.HttpStatus;

public class UsernameEmailAlreadyUsed extends BusinessException {
    public UsernameEmailAlreadyUsed() {
        super("Username and/or email already in use", HttpStatus.CONFLICT);
    }
}