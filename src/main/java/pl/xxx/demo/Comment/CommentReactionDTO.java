package pl.xxx.demo.Comment;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentReactionDTO {
    private String emoji;
    private String username;
}
