package pl.xxx.demo.Comment;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class CommentResponseDTOMapper  {

    public static CommentResponseDTO convertToCommentResponseDTO(Comment comment) {
        return CommentResponseDTO.builder()
                .text(comment.getText())
                .username(comment.getUser().getUsername())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public static List<CommentResponseDTO > convertToCommentResponseDTO(List<Comment> comments) {
        return comments.stream()
                .map(comment -> convertToCommentResponseDTO(comment))
                .toList();
    }

}