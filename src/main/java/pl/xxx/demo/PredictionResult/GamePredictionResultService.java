package pl.xxx.demo.PredictionResult;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameRepository;
import pl.xxx.demo.Prediction.PredictionRepository;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GamePredictionResultService {

    private final GameRepository gameRepository;
    private final PredictionRepository predictionRepository;
    private final UserRepository userRepository;
    private final GamePredictionResultRepository gamePredictionResultRepository;


    public List<GamePredictionResultResponseDTO> getAllPredictionsByUserWithResult() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return gamePredictionResultRepository.findAllPredictionsByUserWithResults(currentUser.getId())
                .stream()
                .map(dto -> new GamePredictionResultResponseDTO(
                        dto.getUsername(),
                        dto.getHomeTeam(),
                        dto.getAwayTeam(),
                        dto.getHomeScore(),
                        dto.getAwayScore(),
                        dto.getPredictedHomeScore(),
                        dto.getPredictedAwayScore(),
                        dto.getPoints()
                ))
                .toList();
    }

    public List<GamePredictionResultResponseDTO> getGameWithPredictionsAndPointsB(Long gameId) {

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Game with id " + gameId + " not found"));

        return gamePredictionResultRepository.findGameWithAllUserPredictionsAndPoints(gameId)
                .stream()
                .map(dto -> new GamePredictionResultResponseDTO(
                        dto.getUsername(),
                        dto.getHomeTeam(),
                        dto.getAwayTeam(),
                        dto.getHomeScore(),
                        dto.getAwayScore(),
                        dto.getPredictedHomeScore(),
                        dto.getPredictedAwayScore(),
                        dto.getPoints()
                ))
                .toList();
    }




    /**
     * FOR FRONTEN
     *
     */
    public List<GamePredictionResultDTO> getAllGamesWithUserPredictionsAndPoints() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return gamePredictionResultRepository.findAllGamesWithUserPedictionAndPoints(currentUser.getId());
    }


    /**
     * FOR FRONTEN
     *
     */
    public List<GamePredictionResultDTO> getGameWithPredictionsAndPoints(Long gameId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Game with id " + gameId + " not found"));
        return gamePredictionResultRepository.findGameWithAllUserPredictionsAndPoints(gameId);
    }

}
