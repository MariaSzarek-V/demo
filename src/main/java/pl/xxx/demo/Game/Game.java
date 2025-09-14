package pl.xxx.demo.Game;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @Column(nullable = false)
    private String homeTeam;

    @Column(nullable = false)
    private String awayTeam;

    private Integer homeScore;
    private Integer awayScore;

    @Column(nullable = false)
    private LocalDateTime gameDate;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus = GameStatus.ADMIN_VIEW;

}
