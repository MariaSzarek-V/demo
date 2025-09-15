package pl.xxx.demo.Comment;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.xxx.demo.Prediction.PredictionService;

import java.util.List;
@Tag(name="7. Comment")
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PredictionService predictionService;
    private final CommentRepository commentRepository;

    @GetMapping
    public List<CommentResponseDTO> getAllComments() {
        return commentService.getAllComments();
    }

    @PostMapping
    public CommentResponseDTO createComment(@Valid @RequestBody CommentRequestDTO dto) {
        return commentService.add(dto);
    }

//    @PostMapping
//    public ResponseEntity<CommentResponseDTO> addComment(@RequestBody @Valid CommentRequestDTO request) {
//        CommentResponseDTO savedComment = commentService.addComment(request); // serwis sam ustali username
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
//    }


}