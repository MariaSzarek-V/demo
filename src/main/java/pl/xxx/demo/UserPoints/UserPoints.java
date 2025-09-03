package pl.xxx.demo.UserPoints;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Prediction.Prediction;
import pl.xxx.demo.User.User;


@Entity
@Getter
@Setter
public class UserPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer points;
    @ManyToOne
    private User user;
    @ManyToOne
    private Game game;
    @ManyToOne
    private Prediction prediction;
}
