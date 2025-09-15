package pl.xxx.demo.Comment;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentRequestDTOMapper  {

    public static CommentRequestDTO convertToCommentRequestDTO(Comment comment) {
        return CommentRequestDTO.builder()
                .text(comment.getText())
//                .username(comment.getUser().getUsername())
                .build();
    }


    public static List<CommentRequestDTO > convertToCommentResponseDTO(List<Comment> comments) {
        return comments.stream()
                .map(comment -> convertToCommentRequestDTO(comment))
                .toList();
    }

}