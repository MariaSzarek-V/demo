package pl.xxx.demo.Notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private Long gameId;
    private String homeTeam;
    private String awayTeam;
    private String homeCountryCode;
    private String awayCountryCode;
    private LocalDateTime gameDate;
    private Long minutesUntilStart;
}
