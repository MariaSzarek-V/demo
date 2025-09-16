package pl.xxx.demo.Game;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.xxx.demo.Enum.GameStatus;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class GameResponseDTO {

    private Long gameId;
    private String homeTeam;
    private String awayTeam;
    private Integer homeScore;
    private Integer awayScore;
    private LocalDateTime gameDate;
    private GameStatus gameStatus;

}
