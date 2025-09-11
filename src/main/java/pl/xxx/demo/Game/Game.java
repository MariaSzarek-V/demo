package pl.xxx.demo.Game;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import pl.xxx.demo.Enum.GameStatus;

import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String homeTeam;
    @NotBlank
    private String awayTeam;
    private Integer homeScore;
    private Integer awayScore;
    @NotBlank
    private LocalDateTime gameDate;
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus = GameStatus.SCHEDULED;

}
