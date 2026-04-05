package pl.xxx.demo.ChatMessage;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ChatMessageResponseDTOMapper  {

    public static ChatMessageResponseDTO convertToChatMessageResponseDTO(ChatMessage message) {
        List<ChatMessageReactionDTO> reactionDTOs = message.getReactions() != null
            ? message.getReactions().stream()
                .map(reaction -> ChatMessageReactionDTO.builder()
                    .emoji(reaction.getEmoji())
                    .username(reaction.getUsername())
                    .build())
                .collect(Collectors.toList())
            : List.of();

        Long parentMessageId = null;
        String parentMessageText = null;
        String parentMessageUsername = null;
        String parentMessageAvatarUrl = null;

        if (message.getParentMessage() != null) {
            parentMessageId = message.getParentMessage().getId();
            parentMessageText = message.getParentMessage().getText();
            parentMessageUsername = message.getParentMessage().getUser().getUsername();
            parentMessageAvatarUrl = message.getParentMessage().getUser().getAvatarUrl();
        }

        Long quotedMessageId = null;
        String quotedMessageText = null;
        String quotedMessageUsername = null;

        if (message.getQuotedMessage() != null) {
            quotedMessageId = message.getQuotedMessage().getId();
            quotedMessageText = message.getQuotedMessage().getText();
            quotedMessageUsername = message.getQuotedMessage().getUser().getUsername();
        }

        return ChatMessageResponseDTO.builder()
                .id(message.getId())
                .text(message.getText())
                .username(message.getUser().getUsername())
                .avatarUrl(message.getUser().getAvatarUrl())
                .createdAt(message.getCreatedAt())
                .reactions(reactionDTOs)
                .parentMessageId(parentMessageId)
                .parentMessageText(parentMessageText)
                .parentMessageUsername(parentMessageUsername)
                .parentMessageAvatarUrl(parentMessageAvatarUrl)
                .quotedMessageId(quotedMessageId)
                .quotedMessageText(quotedMessageText)
                .quotedMessageUsername(quotedMessageUsername)
                .build();
    }

    public static List<ChatMessageResponseDTO > convertToChatMessageResponseDTO(List<ChatMessage> messages) {
        return messages.stream()
                .map(message -> convertToChatMessageResponseDTO(message))
                .toList();
    }

}
