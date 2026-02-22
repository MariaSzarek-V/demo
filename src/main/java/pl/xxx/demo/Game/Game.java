package pl.xxx.demo.Game;

import jakarta.persistence.*;
import lombok.*;
import pl.xxx.demo.Country.Country;
import pl.xxx.demo.Enum.GameStatus;
import pl.xxx.demo.Group.Group;

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

    @ManyToOne
    @JoinColumn(name = "home_country_id", nullable = false)
    private Country homeCountry;

    @ManyToOne
    @JoinColumn(name = "away_country_id", nullable = false)
    private Country awayCountry;

    private Integer homeScore;
    private Integer awayScore;

    @Column(nullable = false)
    private LocalDateTime gameDate;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus = GameStatus.ADMIN_VIEW;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

}
