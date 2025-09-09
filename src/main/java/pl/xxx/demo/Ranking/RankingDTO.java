package pl.xxx.demo.Ranking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.xxx.demo.User.UserDTO;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RankingDTO {
    private UserDTO user;
    private Integer totalPoints;

}

