package pl.xxx.demo.Post;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCommentRequestDTO {
    @NotBlank(message = "Treść komentarza nie może być pusta")
    private String text;

    private Long parentCommentId;
    private Long quotedCommentId;
}
