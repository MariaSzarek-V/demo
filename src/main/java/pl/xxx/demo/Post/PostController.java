package pl.xxx.demo.Post;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @GetMapping("/unread-count")
    public ResponseEntity<Long> getUnreadPostsCount(@RequestParam(required = false) Long leagueId) {
        Long count = postService.getUnreadPostsCount(leagueId);
        return ResponseEntity.ok(count);
    }

    @PostMapping("/upload-image")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            // Validate file
            if (file.isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Plik jest pusty");
                return ResponseEntity.badRequest().body(error);
            }

            // Validate file type
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Plik musi być obrazem");
                return ResponseEntity.badRequest().body(error);
            }

            // Validate file size (max 5MB)
            if (file.getSize() > 5 * 1024 * 1024) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Plik jest za duży (max 5MB)");
                return ResponseEntity.badRequest().body(error);
            }

            // Generate unique filename
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String newFilename = UUID.randomUUID().toString() + fileExtension;

            // Save file
            Path uploadPath = Paths.get("/app/uploads");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(newFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Return URL
            String imageUrl = "/uploads/" + newFilename;
            Map<String, String> response = new HashMap<>();
            response.put("imageUrl", imageUrl);
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Błąd podczas zapisywania pliku: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
