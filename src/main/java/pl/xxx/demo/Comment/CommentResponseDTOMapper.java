package pl.xxx.demo.Comment;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class CommentResponseDTOMapper  {

    public static CommentResponseDTO convertToCommentResponseDTO(Comment comment) {
        List<CommentReactionDTO> reactionDTOs = comment.getReactions() != null
            ? comment.getReactions().stream()
                .map(reaction -> CommentReactionDTO.builder()
                    .emoji(reaction.getEmoji())
                    .username(reaction.getUsername())
                    .build())
                .collect(Collectors.toList())
            : List.of();

        Long parentCommentId = null;
        String parentCommentText = null;
        String parentCommentUsername = null;
        String parentCommentAvatarUrl = null;

        if (comment.getParentComment() != null) {
            parentCommentId = comment.getParentComment().getId();
            parentCommentText = comment.getParentComment().getText();
            parentCommentUsername = comment.getParentComment().getUser().getUsername();
            parentCommentAvatarUrl = comment.getParentComment().getUser().getAvatarUrl();
        }

        Long postId = comment.getPost() != null ? comment.getPost().getId() : null;

        Long quotedCommentId = null;
        String quotedCommentText = null;
        String quotedCommentUsername = null;

        if (comment.getQuotedComment() != null) {
            quotedCommentId = comment.getQuotedComment().getId();
            quotedCommentText = comment.getQuotedComment().getText();
            quotedCommentUsername = comment.getQuotedComment().getUser().getUsername();
        }

        return CommentResponseDTO.builder()
                .id(comment.getId())
                .text(comment.getText())
                .username(comment.getUser().getUsername())
                .avatarUrl(comment.getUser().getAvatarUrl())
                .createdAt(comment.getCreatedAt())
                .reactions(reactionDTOs)
                .parentCommentId(parentCommentId)
                .parentCommentText(parentCommentText)
                .parentCommentUsername(parentCommentUsername)
                .parentCommentAvatarUrl(parentCommentAvatarUrl)
                .postId(postId)
                .quotedCommentId(quotedCommentId)
                .quotedCommentText(quotedCommentText)
                .quotedCommentUsername(quotedCommentUsername)
                .build();
    }

    public static List<CommentResponseDTO > convertToCommentResponseDTO(List<Comment> comments) {
        return comments.stream()
                .map(comment -> convertToCommentResponseDTO(comment))
                .toList();
    }

}