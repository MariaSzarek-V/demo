package pl.xxx.demo.Post;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private String gifUrl;
    private String username;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<PostReactionDTO> reactions;
    private Integer commentsCount;
    private boolean deleted;
    private String deletedBy;
}
