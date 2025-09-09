package pl.xxx.demo.Prediction;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.xxx.demo.Game.GameRepository;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Transactional
@Service
public class PredictionService {
    private final PredictionRepository predictionRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;


    public Prediction add(Prediction prediction) {
        return predictionRepository.save(prediction);
    }

//    public Prediction add(Long GameId, PredictionDTO predictionDTO, User user) {
//        Prediction prediction = PredictionMapper.toEntity(predictionDTO);
//        return predictionRepository.save(prediction);
//    }
//
//    @Transactional
//    public Prediction add(Long gameId, PredictionDTO predictionDTO) {
//        // Pobranie zalogowanego użytkownika
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated()) {
//            throw new SecurityException("User is not authenticated");
//        }
//
//        String username = authentication.getName();
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//
//        // Pobranie meczu
//        Game game = gameRepository.findById(gameId)
//                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + gameId));
//
//        // Mapowanie DTO → encja
//        Prediction prediction = PredictionMapper.toEntity(predictionDTO);
//        prediction.setGame(game);
//        prediction.setUser(user);
//
//        // Sprawdzenie, czy użytkownik już przewidział ten mecz
//        if (predictionRepository.existsByGameAndUser(game, user)) {
//            throw new IllegalStateException("Prediction already exists for this game and user");
//        }
//
//        // Zapis
//        return predictionRepository.save(prediction);
//    }


    public List<Prediction> getAll() {
        return predictionRepository.findAll();
    }

//    public List<PredictionDTO> getPredictionsByUserId(Long userId) {
//        List<Prediction> predictions = predictionRepository.findByUserId(userId);
//        return predictionMapper.
//    }

//
//    public Optional<Prediction> get(Long id) {
//        return predictionRepository.findById(id);
//    }

    public List<Prediction> getMyPredictions() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<Prediction> myPredictions = predictionRepository.findByUserId(currentUser.getId());
        return myPredictions;
    }

    public void update(Long id, Prediction prediction) {
        Prediction existingPrediction = predictionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No such prediction to update"));
        existingPrediction.setPredictedHomeScore(prediction.getPredictedHomeScore());
        existingPrediction.setPredictedAwayScore(prediction.getPredictedAwayScore());
    }

    /*
    metoda do view controllera, nie do rest api
     */
    public void saveOrUpdate(Long predictionId, Long gameId, int homeScore, int awayScore) {
        Prediction prediction;

        if (predictionId != null) {
            prediction = predictionRepository.findById(predictionId)
                    .orElseThrow(() -> new RuntimeException("Prediction not found"));
        } else {
            prediction = new Prediction();
            prediction.setGame(gameRepository.findById(gameId)
                    .orElseThrow(() -> new RuntimeException("Game not found")));
        }

        prediction.setPredictedHomeScore(homeScore);
        prediction.setPredictedAwayScore(awayScore);
        predictionRepository.save(prediction);
    }

    public void delete(Long id) {
        predictionRepository.deleteById(id);
    }
}
