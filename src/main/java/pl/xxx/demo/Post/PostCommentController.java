package pl.xxx.demo.Post;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "9. Post Comments")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostCommentController {

    private final PostCommentService commentService;

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<PostCommentResponseDTO>> getCommentsByPostId(@PathVariable Long postId) {
        List<PostCommentResponseDTO> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<PostCommentResponseDTO> createComment(
            @PathVariable Long postId,
            @Valid @RequestBody PostCommentRequestDTO dto
    ) {
        PostCommentResponseDTO comment = commentService.createComment(postId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<PostCommentResponseDTO> updateComment(
            @PathVariable Long commentId,
            @Valid @RequestBody PostCommentRequestDTO dto
    ) {
        PostCommentResponseDTO comment = commentService.updateComment(commentId, dto);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/comments/{commentId}/reactions")
    public ResponseEntity<PostCommentResponseDTO> addReaction(
            @PathVariable Long commentId,
            @RequestParam String emoji
    ) {
        PostCommentResponseDTO comment = commentService.addReaction(commentId, emoji);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/comments/{commentId}/reactions")
    public ResponseEntity<PostCommentResponseDTO> removeReaction(
            @PathVariable Long commentId,
            @RequestParam String emoji
    ) {
        PostCommentResponseDTO comment = commentService.removeReaction(commentId, emoji);
        return ResponseEntity.ok(comment);
    }
}
