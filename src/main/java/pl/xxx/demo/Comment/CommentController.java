package pl.xxx.demo.Comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.xxx.demo.User.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<CommentResponseDTO> getAllComments() {
        return commentService.getAllComments();
    }

    @PostMapping
    public ResponseEntity<CommentResponseDTO> addComment(@RequestBody CommentRequestDTO request) {

        CommentResponseDTO savedComment = commentService.addComment(request);
        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 Created
                .body(savedComment);        // zwracamy zapisany obiekt
    }
}