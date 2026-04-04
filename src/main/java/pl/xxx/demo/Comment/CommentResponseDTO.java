package pl.xxx.demo.Comment;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentResponseDTO {
    private Long id;
    private String text;
    private String username;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private List<CommentReactionDTO> reactions;
    private Long parentCommentId;
    private String parentCommentText;
    private String parentCommentUsername;
    private String parentCommentAvatarUrl;
    private Long postId;
    private Long quotedCommentId;
    private String quotedCommentText;
    private String quotedCommentUsername;
}
