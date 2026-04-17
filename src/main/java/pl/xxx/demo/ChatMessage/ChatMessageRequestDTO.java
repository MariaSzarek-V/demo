package pl.xxx.demo.ChatMessage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatMessageRequestDTO {
    @NotBlank(message = "Wiadomość nie może być pusta")
    @Size(max = 1000, message = "Wiadomość może mieć maksymalnie 1000 znaków")
    private String text;

    private Long parentMessageId;
    private Long quotedMessageId;

    @NotNull(message = "League ID jest wymagane")
    private Long leagueId;
}
