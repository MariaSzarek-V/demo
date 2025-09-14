package pl.xxx.demo.PredictionResult;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.Prediction.Prediction;
import pl.xxx.demo.Prediction.PredictionRepository;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;
import pl.xxx.demo.User.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PredictionResultService {

    private final PredictionRepository predictionRepository;
    private final GamePredictionResultRepository gamePredictionResultRepository;
    private final UserRepository userRepository;


//TODO: zamiast tego -getAllPredictionsAndPointsForUser

//    public List<PredictionResultDTO> getPredictionResults() {
//        return predictionRepository.findAll().stream()
//                .map(predictionResultMapper::convertToPredictionResultDTO)
//                .collect(Collectors.toList());
//    }




}
