package pl.xxx.demo.League;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserLeagueDTO {
    private Long id;
    private Long userId;
    private String username;
    private Long leagueId;
    private String leagueName;
    private LocalDateTime joinedAt;
    private Boolean isAdmin;
}
