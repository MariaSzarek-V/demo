package pl.xxx.demo.RankingHistory;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
        name = "ranking_history",
        uniqueConstraints = @UniqueConstraint(columnNames = {"gameId", "userId"})
)
public class RankingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    private Long gameId;
    private Long userId;
    private String username;
    private Integer totalPoints;






}
