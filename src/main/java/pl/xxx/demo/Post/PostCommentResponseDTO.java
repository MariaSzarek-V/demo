package pl.xxx.demo.Post;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCommentResponseDTO {
    private Long id;
    private String text;
    private String username;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<PostCommentReactionDTO> reactions;

    // Quoted comment info
    private Long quotedCommentId;
    private String quotedCommentText;
    private String quotedCommentUsername;
}
