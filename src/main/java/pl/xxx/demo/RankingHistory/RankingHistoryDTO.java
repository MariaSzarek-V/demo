package pl.xxx.demo.RankingHistory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RankingHistoryDTO {

    @JsonIgnore
    private Long gameId;
    private String username;
    private Integer totalPoints;
    private Integer position;
    private Integer positionChange;
}