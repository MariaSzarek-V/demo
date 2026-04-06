package pl.xxx.demo.Post;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostReactionDTO {
    private String emoji;
    private String username;
}
