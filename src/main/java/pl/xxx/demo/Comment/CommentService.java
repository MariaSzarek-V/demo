package pl.xxx.demo.Comment;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.Post.Post;
import pl.xxx.demo.Post.PostRepository;
import pl.xxx.demo.User.UserRepository;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<CommentResponseDTO> getAllComments() {
        List<Comment> com = commentRepository.findAllByOrderByCreatedAtDesc();
        return CommentResponseDTOMapper.convertToCommentResponseDTO(com);
    }

    public List<CommentResponseDTO> getCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        List<Comment> comments = commentRepository.findByPostOrderByCreatedAtAsc(post);
        return CommentResponseDTOMapper.convertToCommentResponseDTO(comments);
    }

    public CommentResponseDTO add(CommentRequestDTO dto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Comment comment = Comment.builder()
                .text(dto.getText())
                .createdAt(LocalDateTime.now())
                .user(userRepository.findByUsername(username)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found")))
                .build();

        // Set parent comment if provided
        if (dto.getParentCommentId() != null) {
            Comment parentComment = commentRepository.findById(dto.getParentCommentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent comment not found"));
            comment.setParentComment(parentComment);
        }

        // Set post if provided
        if (dto.getPostId() != null) {
            Post post = postRepository.findById(dto.getPostId())
                    .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
            comment.setPost(post);
        }

        // Set quoted comment if provided
        if (dto.getQuotedCommentId() != null) {
            Comment quotedComment = commentRepository.findById(dto.getQuotedCommentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Quoted comment not found"));
            comment.setQuotedComment(quotedComment);
        }

        Comment saved = commentRepository.save(comment);
        return CommentResponseDTOMapper.convertToCommentResponseDTO(saved);
    }

    public CommentResponseDTO addReaction(Long commentId, String emoji) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        // Check if user already reacted with this emoji
        boolean alreadyReacted = comment.getReactions().stream()
                .anyMatch(r -> r.getUsername().equals(username) && r.getEmoji().equals(emoji));

        if (!alreadyReacted) {
            CommentReaction reaction = new CommentReaction(emoji, username);
            comment.getReactions().add(reaction);
            Comment saved = commentRepository.save(comment);
            return CommentResponseDTOMapper.convertToCommentResponseDTO(saved);
        }

        return CommentResponseDTOMapper.convertToCommentResponseDTO(comment);
    }

    public CommentResponseDTO removeReaction(Long commentId, String emoji) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        comment.getReactions().removeIf(r ->
                r.getUsername().equals(username) && r.getEmoji().equals(emoji));

        Comment saved = commentRepository.save(comment);
        return CommentResponseDTOMapper.convertToCommentResponseDTO(saved);
    }
}
