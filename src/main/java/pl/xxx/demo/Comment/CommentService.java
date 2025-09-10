package pl.xxx.demo.Comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public List<CommentResponseDTO> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(comment -> new CommentResponseDTO(
                        comment.getId(),
                        comment.getText(),
                        comment.getUser().getUsername(),
                        comment.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public CommentResponseDTO addComment(CommentRequestDTO request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setText(request.getText());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(user);

        Comment saved = commentRepository.save(comment);

        // Mapowanie encji na DTO
        return new CommentResponseDTO(
                saved.getId(),
                saved.getText(),
                saved.getUser().getUsername(),
                saved.getCreatedAt()

        );
    }
}
