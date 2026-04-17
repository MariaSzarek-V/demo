package pl.xxx.demo.RankingHistory;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pl.xxx.demo.League.League;
import pl.xxx.demo.User.User;



@Entity
@Getter
@Setter
//gwarancja, że jeden user ma jeden wpis rankingu na jeden mecz w danej lidze
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"game_id", "user_id", "league_id"})
)
public class RankingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long gameId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private League league;

    @JoinColumn(nullable = false)
    private Integer totalPoints;

    @Column(nullable = false)
    private Integer position;

    @Column(nullable = false)
    private Integer positionChange;
}
