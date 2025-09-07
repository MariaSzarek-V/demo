package pl.xxx.demo.Ranking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RankingDTO {
    private Long userId;
    private String username;
    private Integer totalPoints;


    public RankingDTO(Long userId, String username, Integer totalPoints) {
        this.userId = userId;
        this.username = username;
        this.totalPoints = totalPoints;
    }
}

