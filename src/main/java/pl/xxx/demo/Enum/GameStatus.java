package pl.xxx.demo.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import pl.xxx.demo.Error.IllegalGameStatusException;

public enum GameStatus {
    ADMIN_VIEW,
    SCHEDULED,
    FINISHED;

    @JsonCreator
    public static GameStatus fromString(String value) {
        for (GameStatus status : GameStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }throw new IllegalGameStatusException();
    }
}
