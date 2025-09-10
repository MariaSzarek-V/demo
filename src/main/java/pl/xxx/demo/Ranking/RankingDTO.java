package pl.xxx.demo.Ranking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.xxx.demo.User.UserResponseDTO;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RankingDTO {
    private String username;
    private Integer totalPoints;

}
