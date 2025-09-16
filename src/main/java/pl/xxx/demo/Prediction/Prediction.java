package pl.xxx.demo.Prediction;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.User.User;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "game_id"}))
public class Prediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer predictedHomeScore;
    @Column(nullable = false)
    private Integer predictedAwayScore;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
    @Builder.Default
    @Column(nullable = false)
    private Boolean calculated = false;
}
