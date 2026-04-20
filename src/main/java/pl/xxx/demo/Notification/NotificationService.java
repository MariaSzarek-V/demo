package pl.xxx.demo.Notification;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Enum.GameStatus;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameRepository;
import pl.xxx.demo.Prediction.Prediction;
import pl.xxx.demo.Prediction.PredictionRepository;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final GameRepository gameRepository;
    private final PredictionRepository predictionRepository;
    private final UserService userService;

    public List<NotificationDTO> getUpcomingGamesWithoutPrediction() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName());

        // Get all upcoming games
        List<Game> upcomingGames = gameRepository.findByGameStatus(GameStatus.SCHEDULED);

        // Get all user's predictions
        List<Prediction> userPredictions = predictionRepository.findByUserId(currentUser.getId());

        List<NotificationDTO> notifications = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (Game game : upcomingGames) {
            // Check if user has prediction for this game
            boolean hasPrediction = userPredictions.stream()
                    .anyMatch(p -> p.getGame().getId().equals(game.getId()));

            if (!hasPrediction) {
                // Calculate minutes until game starts
                long minutesUntilStart = Duration.between(now, game.getGameDate()).toMinutes();

                // Only notify if game starts within 24 hours (1440 minutes)
                if (minutesUntilStart > 0 && minutesUntilStart <= 1440) {
                    NotificationDTO notification = NotificationDTO.builder()
                            .gameId(game.getId())
                            .homeTeam(game.getHomeCountry().getName())
                            .awayTeam(game.getAwayCountry().getName())
                            .homeCountryCode(game.getHomeCountry().getCode())
                            .awayCountryCode(game.getAwayCountry().getCode())
                            .gameDate(game.getGameDate())
                            .minutesUntilStart(minutesUntilStart)
                            .build();
                    notifications.add(notification);
                }
            }
        }

        // Sort by closest game first
        notifications.sort((a, b) -> a.getMinutesUntilStart().compareTo(b.getMinutesUntilStart()));

        return notifications;
    }
}
