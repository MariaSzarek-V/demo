package pl.xxx.demo;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.xxx.demo.Error.*;

import java.util.HashMap;
import java.util.Map;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GameTimeStatusException.class)
    public ResponseEntity<Map<String, Object>> handleGameTimeStatusException(GameTimeStatusException e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(PredictionEditNotAllowedException.class)
    public ResponseEntity<Map<String, Object>> handlePredictionEditNotAllowed(PredictionEditNotAllowedException e) {
        return  buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoRankingAvailableException.class)
    public ResponseEntity<Map<String, Object>> handleNoRankingAvailableException(NoRankingAvailableException e) {
        return  buildErrorResponse(e.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(GameDeleteNotAllowedException.class)
    public ResponseEntity<Map<String, Object>> handleNoRankingAvailableException(GameDeleteNotAllowedException e) {
        return  buildErrorResponse(e.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(PredictionAlreadyExistForGameException.class)
    public ResponseEntity<Map<String, Object>> handlePredictionAlreadyExistForGame(PredictionAlreadyExistForGameException e) {
        return  buildErrorResponse(e.getMessage(), HttpStatus.CONFLICT);
    }


    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status) {
        return buildErrorResponse(message, status, null);
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status, Map<String, String> errors) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", status.value());
        if (errors !=null && !errors.isEmpty()) {
            response.put("errors", errors);
        }
        return ResponseEntity.status(status).body(response);
    }


    /**
     * metoda do @Valid
     *
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );
        return buildErrorResponse("Błąd walidacji", HttpStatus.BAD_REQUEST, fieldErrors);
    }

}
