package pl.xxx.demo.Prediction;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.xxx.demo.Enum.GameStatus;
import pl.xxx.demo.Error.PredictionEditNotAllowedException;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameRepository;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Transactional
@Service
public class PredictionService {
    private final PredictionRepository predictionRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;



    public PredictionResponseDTO add(PredictionRequestDTO dto) {
        Game game = gameRepository.findById(dto.getGameId())
                .orElseThrow(() -> new ResourceNotFoundException("Game not found"));
        if (!game.getGameStatus().equals(GameStatus.SCHEDULED)) {
            throw new PredictionEditNotAllowedException();
        } else {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            Prediction prediction = PredictionRequestDTOMapper.convertToPrediction(dto, game, user);
            predictionRepository.save(prediction);
            return PredictionResponseDTOMapper.convertToPredictionResponseDTO(prediction);
        }
    }


    public PredictionResponseDTO get(Long id) {
        Prediction prediction = predictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prediction with id " + id + " not found"));
        return PredictionResponseDTOMapper.convertToPredictionResponseDTO(prediction);
    }

    /*
zwraca predictions dla zalogowanego usera
 */

    public List<PredictionResponseDTO> getMyPredictions() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        List<Prediction> myPredictions = predictionRepository.findByUserId(currentUser.getId());
        return PredictionResponseDTOMapper.convertToPredictionResponseDTO(myPredictions);
    }


    public List<PredictionResponseDTO> getMyPredictionsByGameStatus(GameStatus status) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Prediction> myPredictions = predictionRepository.findByUserIdAndGameGameStatus(currentUser.getId(), status);
        return PredictionResponseDTOMapper.convertToPredictionResponseDTO(myPredictions);
    }



    public PredictionResponseDTO  update(Long id, PredictionRequestDTO dto) {
        Prediction existingPrediction = predictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such prediction to update"));

        Game game = existingPrediction.getGame();

        if (game.getGameDate().isBefore(LocalDateTime.now(ZoneOffset.UTC))) {
            throw new PredictionEditNotAllowedException();
        }
        existingPrediction.setPredictedHomeScore(dto.getPredictedHomeScore());
        existingPrediction.setPredictedAwayScore(dto.getPredictedAwayScore());

        return PredictionResponseDTOMapper.convertToPredictionResponseDTO(existingPrediction);
    }


    public List<PredictionResponseDTO> getPredictionsByGameId(Long gameId) {
        List<Prediction> predictions = predictionRepository.findByGameId(gameId);
        return PredictionResponseDTOMapper.convertToPredictionResponseDTO(predictions);
    }

    public List<Prediction> getPredictionsForScheduledGames() {
        List<Prediction> predictions = predictionRepository.findByGameGameStatus(GameStatus.SCHEDULED);
        return predictions;
    }

    /*
    metoda do view controllera, nie do rest api
     */
//    public void saveOrUpdate(Long predictionId, Long gameId, int homeScore, int awayScore) {
//        Prediction prediction;
//
//        if (predictionId != null) {
//            prediction = predictionRepository.findById(predictionId)
//                    .orElseThrow(() -> new RuntimeException("Prediction not found"));
//        } else {
//            prediction = new Prediction();
//            prediction.setGame(gameRepository.findById(gameId)
//                    .orElseThrow(() -> new RuntimeException("Game not found")));
//        }
//
//        prediction.setPredictedHomeScore(homeScore);
//        prediction.setPredictedAwayScore(awayScore);
//        predictionRepository.save(prediction);
//    }

    public void delete(Long id) {
        predictionRepository.deleteById(id);
    }
}
