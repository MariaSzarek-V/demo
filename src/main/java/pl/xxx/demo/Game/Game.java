package pl.xxx.demo.Game;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.xxx.demo.Enum.GameStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String homeTeam;
    private String awayTeam;
    private Integer homeScore;
    private Integer awayScore;
    private LocalDateTime gameDate;
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus = GameStatus.SCHEDULED;


}
