package pl.xxx.demo.Post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestDTO {
    @NotBlank(message = "Tytuł nie może być pusty")
    @Size(max = 255, message = "Tytuł nie może przekraczać 255 znaków")
    private String title;

    @NotBlank(message = "Treść nie może być pusta")
    private String content;

    private String imageUrl;

    @NotNull(message = "League ID jest wymagane")
    private Long leagueId;
}
