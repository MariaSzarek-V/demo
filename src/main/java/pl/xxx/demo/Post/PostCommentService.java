package pl.xxx.demo.Post;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostCommentService {

    private final PostCommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostCommentResponseDTOMapper commentMapper;

    @Transactional(readOnly = true)
    public List<PostCommentResponseDTO> getCommentsByPostId(Long postId) {
        List<PostComment> comments = commentRepository.findAllByPostIdOrderByCreatedAtDesc(postId);
        return comments.stream()
                .map(commentMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostCommentResponseDTO createComment(Long postId, PostCommentRequestDTO dto) {
        User currentUser = getCurrentUser();

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post nie znaleziony"));

        PostComment comment = PostComment.builder()
                .text(dto.getText())
                .user(currentUser)
                .post(post)
                .build();

        // Set quoted comment if exists
        if (dto.getQuotedCommentId() != null) {
            PostComment quotedComment = commentRepository.findById(dto.getQuotedCommentId())
                    .orElseThrow(() -> new RuntimeException("Cytowany komentarz nie znaleziony"));
            comment.setQuotedComment(quotedComment);
        }

        PostComment savedComment = commentRepository.save(comment);
        return commentMapper.map(savedComment);
    }

    @Transactional
    public PostCommentResponseDTO updateComment(Long commentId, PostCommentRequestDTO dto) {
        PostComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Komentarz nie znaleziony"));

        User currentUser = getCurrentUser();
        if (!comment.getUser().getId().equals(currentUser.getId()) && !isAdmin()) {
            throw new RuntimeException("Brak uprawnień do edycji tego komentarza");
        }

        comment.setText(dto.getText());
        // Reset all reactions after editing
        comment.getReactions().clear();
        PostComment updatedComment = commentRepository.save(comment);
        return commentMapper.map(updatedComment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        PostComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Komentarz nie znaleziony"));

        User currentUser = getCurrentUser();
        if (!comment.getUser().getId().equals(currentUser.getId()) && !isAdmin()) {
            throw new RuntimeException("Brak uprawnień do usunięcia tego komentarza");
        }

        commentRepository.delete(comment);
    }

    @Transactional
    public PostCommentResponseDTO addReaction(Long commentId, String emoji) {
        PostComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Komentarz nie znaleziony"));

        String currentUsername = getCurrentUser().getUsername();

        // Check if reaction already exists
        boolean exists = comment.getReactions().stream()
                .anyMatch(r -> r.getUsername().equals(currentUsername) && r.getEmoji().equals(emoji));

        if (!exists) {
            PostCommentReaction reaction = PostCommentReaction.builder()
                    .emoji(emoji)
                    .username(currentUsername)
                    .build();
            comment.getReactions().add(reaction);
            commentRepository.save(comment);
        }

        return commentMapper.map(comment);
    }

    @Transactional
    public PostCommentResponseDTO removeReaction(Long commentId, String emoji) {
        PostComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Komentarz nie znaleziony"));

        String currentUsername = getCurrentUser().getUsername();

        comment.getReactions().removeIf(r ->
                r.getUsername().equals(currentUsername) && r.getEmoji().equals(emoji));

        commentRepository.save(comment);
        return commentMapper.map(comment);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Użytkownik nie znaleziony"));
    }

    private boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }
}
