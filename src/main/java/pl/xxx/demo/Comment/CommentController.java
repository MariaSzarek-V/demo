package pl.xxx.demo.Comment;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name="7. Comment")
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
    public CommentResponseDTO createComment(@Valid @RequestBody CommentRequestDTO dto) {
        return commentService.add(dto);
    }
}