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



    @ExceptionHandler({
            GameTimeStatusException.class,
            PredictionEditNotAllowedException.class,
            NoRankingAvailableException.class,
            GameDeleteNotAllowedException.class,
            PredictionAlreadyExistForGameException.class,
            ResourceNotFoundException.class,
            UsernameEmailAlreadyUsed.class
    })
    public ResponseEntity<Map<String, Object>> handleBusinessExceptions(BusinessException e) {
        return buildErrorResponse(e.getMessage(), e.getStatus());
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
