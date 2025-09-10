package pl.xxx.demo.RankingHistory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.xxx.demo.Ranking.RankingDTO;
import pl.xxx.demo.User.UserResponseDTO;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RankingHistoryDTO {

    private Long gameId;
    private String username;
    private Integer totalPoints;
    private Integer position;
    private Integer positionChange;
}