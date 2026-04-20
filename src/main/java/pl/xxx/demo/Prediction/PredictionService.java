package pl.xxx.demo.Prediction;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.xxx.demo.Enum.GameStatus;
import pl.xxx.demo.Error.PredictionAlreadyExistForGameException;
import pl.xxx.demo.Error.PredictionEditNotAllowedException;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameRepository;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;
import java.time.LocalDateTime;
import java.util.List;



@RequiredArgsConstructor
@Transactional
@Service
public class PredictionService {
    private final PredictionRepository predictionRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final PredictionRequestDTOMapper predictionRequestDTOMapper;


    public PredictionResponseDTO add(PredictionRequestDTO dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Prediction predictionCheck = predictionRepository.findByUserIdAndGameId(user.getId(), dto.getGameId());
        if (predictionCheck != null) {
            throw new PredictionAlreadyExistForGameException();
        }
        else{
            Game game = gameRepository.findById(dto.getGameId())
                    .orElseThrow(() -> new ResourceNotFoundException("Game not found"));
            if (!game.getGameStatus().equals(GameStatus.SCHEDULED)) {
                throw new PredictionEditNotAllowedException();
            } else {
                Prediction prediction = PredictionRequestDTOMapper.convertToPrediction(dto, game, user);
                predictionRepository.save(prediction);
                return PredictionResponseDTOMapper.convertToPredictionResponseDTO(prediction);
            }
        }
    }


    public PredictionResponseDTO get(Long id) {
        Prediction prediction = predictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prediction with id " + id + " not found"));
        return PredictionResponseDTOMapper.convertToPredictionResponseDTO(prediction);
    }


    public List<PredictionResponseDTO> getMyPredictions() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        List<Prediction> myPredictions = predictionRepository.findByUserId(currentUser.getId());
        return PredictionResponseDTOMapper.convertToPredictionResponseDTO(myPredictions);
    }


    public PredictionResponseDTO  update(Long id, PredictionRequestDTO dto) {
        Prediction existingPrediction = predictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such prediction to update"));

        Game game = existingPrediction.getGame();

        if (game.getGameDate().isBefore(LocalDateTime.now())) {
            throw new PredictionEditNotAllowedException();
        }
        if (dto.getGameId() != null && !dto.getGameId().equals(game.getId())) {
            throw new PredictionAlreadyExistForGameException();
        }

        predictionRequestDTOMapper.updatePredictionIfNotNull(existingPrediction, dto);
        return PredictionResponseDTOMapper.convertToPredictionResponseDTO(existingPrediction);
    }


    public void delete(Long id) {
        predictionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prediction with id " + id + " not found"));
        predictionRepository.deleteById(id);
    }

    public List<PredictionHistoryDTO> getMyPredictionHistory(Long leagueId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Prediction> myPredictions = predictionRepository.findByUserIdAndLeagueIdOrderByGameGameDateDesc(
                currentUser.getId(), leagueId);

        return myPredictions.stream()
                .map(prediction -> {
                    Game game = prediction.getGame();
                    return PredictionHistoryDTO.builder()
                            .gameId(game.getId())
                            .homeTeam(game.getHomeCountry().getName())
                            .awayTeam(game.getAwayCountry().getName())
                            .homeCountryCode(game.getHomeCountry().getCode())
                            .awayCountryCode(game.getAwayCountry().getCode())
                            .gameDate(game.getGameDate())
                            .gameStatus(game.getGameStatus().toString())
                            .actualHomeScore(game.getHomeScore())
                            .actualAwayScore(game.getAwayScore())
                            .predictedHomeScore(prediction.getPredictedHomeScore())
                            .predictedAwayScore(prediction.getPredictedAwayScore())
                            .points(calculatePoints(prediction, game))
                            .build();
                })
                .toList();
    }

    private Integer calculatePoints(Prediction prediction, Game game) {
        // Only calculate points for finished games
        if (!game.getGameStatus().equals(GameStatus.FINISHED)) {
            return null;
        }

        if (game.getHomeScore() == null || game.getAwayScore() == null) {
            return null;
        }

        int predHome = prediction.getPredictedHomeScore();
        int predAway = prediction.getPredictedAwayScore();
        int actualHome = game.getHomeScore();
        int actualAway = game.getAwayScore();

        // Exact score: 5 points
        if (predHome == actualHome && predAway == actualAway) {
            return 5;
        }

        // Correct result (win/draw/loss): 3 points
        int predDiff = predHome - predAway;
        int actualDiff = actualHome - actualAway;
        if ((predDiff > 0 && actualDiff > 0) || // Both home win
            (predDiff == 0 && actualDiff == 0) || // Both draw
            (predDiff < 0 && actualDiff < 0)) {   // Both away win
            return 3;
        }

        // Correct goal difference: 1 point
        if (predDiff == actualDiff) {
            return 1;
        }

        // Nothing correct: 0 points
        return 0;
    }
}
