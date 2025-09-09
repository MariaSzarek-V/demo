package pl.xxx.demo.RankingHistory;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.xxx.demo.User.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
        //gwarancja, że jeden user ma jeden wpis rankingu na jeden mecz
        uniqueConstraints = @UniqueConstraint(columnNames = {"game_id", "user_id"})
)
public class RankingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nie Game game, bo mi wystarczy tylko id meczu
    @Column(nullable = false)
    private Long gameId;

    //tutaj caly user i dlatego tez rankignHistoryDTO/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @JoinColumn(nullable = false)
    private Integer totalPoints;

    @Column(nullable = false)
    private Integer position;

    @Column(nullable = false)
    private Integer positionChange;





}
