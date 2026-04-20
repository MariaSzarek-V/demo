package pl.xxx.demo.Notification;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="9. Notifications")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/upcoming-games")
    public List<NotificationDTO> getUpcomingGamesWithoutPrediction() {
        return notificationService.getUpcomingGamesWithoutPrediction();
    }
}
