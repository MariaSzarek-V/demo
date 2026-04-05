package pl.xxx.demo.ChatMessage;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageReaction {
    private String emoji;
    private String username;
}
