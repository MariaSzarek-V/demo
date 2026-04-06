package pl.xxx.demo.Post;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCommentReactionDTO {
    private String emoji;
    private String username;
}
