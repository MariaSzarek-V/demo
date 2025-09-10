package pl.xxx.demo.Comment;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentRequestDTO {
    private String text;
    private String username;
}

