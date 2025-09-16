package pl.xxx.demo.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentRequestDTO {
    @NotBlank(message = "Komentarz nie może być pusty")
    @Size(max = 1000, message = "Komentarz może mieć maksymalnie 1000 znaków")
    private String text;
}

