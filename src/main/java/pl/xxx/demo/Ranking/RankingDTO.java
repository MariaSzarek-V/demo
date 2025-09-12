package pl.xxx.demo.Ranking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RankingDTO {
    private Integer position;
    private String username;
    private Integer totalPoints;
}
