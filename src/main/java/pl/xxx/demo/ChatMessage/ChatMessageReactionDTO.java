package pl.xxx.demo.ChatMessage;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatMessageReactionDTO {
    private String emoji;
    private String username;
}
