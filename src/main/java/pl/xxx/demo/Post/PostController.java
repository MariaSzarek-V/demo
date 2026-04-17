package pl.xxx.demo.Post;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "8. Posts")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<Page<PostResponseDTO>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long leagueId
    ) {
        Page<PostResponseDTO> posts;
        if (leagueId != null) {
            posts = postService.getPostsByLeague(page, size, leagueId);
        } else {
            posts = postService.getAllPosts(page, size);
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long id) {
        PostResponseDTO post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@Valid @RequestBody PostRequestDTO dto) {
        PostResponseDTO createdPost = postService.createPost(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody PostRequestDTO dto
    ) {
        PostResponseDTO updatedPost = postService.updatePost(id, dto);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/reactions")
    public ResponseEntity<PostResponseDTO> addReaction(
            @PathVariable Long id,
            @RequestParam String emoji
    ) {
        PostResponseDTO post = postService.addReaction(id, emoji);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{id}/reactions")
    public ResponseEntity<PostResponseDTO> removeReaction(
            @PathVariable Long id,
            @RequestParam String emoji
    ) {
        PostResponseDTO post = postService.removeReaction(id, emoji);
        return ResponseEntity.ok(post);
    }
}
