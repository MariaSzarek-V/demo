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
//    private Long userId;
//    private String username;
    private UserDTO user;
    private Integer totalPoints;

//to stary konstruktor , do wywalenia jak zrefaktoryzuje caly kod
    public RankingDTO(Long userId, String username, Integer totalPoints) {
        this.user = new UserDTO(userId, username);
        this.totalPoints = totalPoints;
    }
}

