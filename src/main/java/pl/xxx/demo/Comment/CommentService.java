package pl.xxx.demo.Comment;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.User.UserRepository;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public List<CommentResponseDTO> getAllComments() {
        List<Comment> com = commentRepository.findAllByOrderByCreatedAtDesc();
        return CommentResponseDTOMapper.convertToCommentResponseDTO(com);
    }

    public CommentResponseDTO add(CommentRequestDTO dto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Comment comment = new Comment();
        comment.setText(dto.getText());
        comment.setCreatedAt(LocalDateTime.now());

        comment.setUser(userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found")));

        Comment saved = commentRepository.save(comment);
        return CommentResponseDTOMapper.convertToCommentResponseDTO(saved);
    }
}
