package pl.xxx.demo.Game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.xxx.demo.Enum.GameStatus;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class GameResponseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long gameId;
    private String homeTeam;
    private String awayTeam;
    private Integer homeScore;
    private Integer awayScore;
    private LocalDateTime gameDate;
    private GameStatus gameStatus;


    public boolean isScheduled() {
        return GameStatus.SCHEDULED.equals(this.gameStatus);
    }

}
