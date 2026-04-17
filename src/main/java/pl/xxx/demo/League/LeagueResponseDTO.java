package pl.xxx.demo.League;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LeagueResponseDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Boolean isActive;
    private Long createdByUserId;
    private String createdByUsername;
    private Long memberCount;
}
