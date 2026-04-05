package pl.xxx.demo.ChatMessage;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatMessageResponseDTO {
    private Long id;
    private String text;
    private String username;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private List<ChatMessageReactionDTO> reactions;
    private Long parentMessageId;
    private String parentMessageText;
    private String parentMessageUsername;
    private String parentMessageAvatarUrl;
    private Long quotedMessageId;
    private String quotedMessageText;
    private String quotedMessageUsername;
}
