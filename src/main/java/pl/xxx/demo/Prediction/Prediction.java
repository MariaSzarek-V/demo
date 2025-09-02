package pl.xxx.demo.Prediction;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.User.User;

@Entity
@Getter
@Setter
public class Prediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer predictedHomeScore;
    private Integer predictedAwayScore;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

}
