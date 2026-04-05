package pl.xxx.demo.ChatMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.User.UserRepository;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    public List<ChatMessageResponseDTO> getAllMessages() {
        List<ChatMessage> messages = chatMessageRepository.findAllByOrderByCreatedAtDesc();
        return ChatMessageResponseDTOMapper.convertToChatMessageResponseDTO(messages);
    }

    public ChatMessageResponseDTO add(ChatMessageRequestDTO dto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        ChatMessage message = ChatMessage.builder()
                .text(dto.getText())
                .createdAt(LocalDateTime.now())
                .user(userRepository.findByUsername(username)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found")))
                .build();

        // Set parent message if provided
        if (dto.getParentMessageId() != null) {
            ChatMessage parentMessage = chatMessageRepository.findById(dto.getParentMessageId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent message not found"));
            message.setParentMessage(parentMessage);
        }

        // Set quoted message if provided
        if (dto.getQuotedMessageId() != null) {
            ChatMessage quotedMessage = chatMessageRepository.findById(dto.getQuotedMessageId())
                    .orElseThrow(() -> new ResourceNotFoundException("Quoted message not found"));
            message.setQuotedMessage(quotedMessage);
        }

        ChatMessage saved = chatMessageRepository.save(message);
        return ChatMessageResponseDTOMapper.convertToChatMessageResponseDTO(saved);
    }

    public ChatMessageResponseDTO addReaction(Long messageId, String emoji) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        ChatMessage message = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found"));

        // Check if user already reacted with this emoji
        boolean alreadyReacted = message.getReactions().stream()
                .anyMatch(r -> r.getUsername().equals(username) && r.getEmoji().equals(emoji));

        if (!alreadyReacted) {
            ChatMessageReaction reaction = new ChatMessageReaction(emoji, username);
            message.getReactions().add(reaction);
            ChatMessage saved = chatMessageRepository.save(message);
            return ChatMessageResponseDTOMapper.convertToChatMessageResponseDTO(saved);
        }

        return ChatMessageResponseDTOMapper.convertToChatMessageResponseDTO(message);
    }

    public ChatMessageResponseDTO removeReaction(Long messageId, String emoji) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        ChatMessage message = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found"));

        message.getReactions().removeIf(r ->
                r.getUsername().equals(username) && r.getEmoji().equals(emoji));

        ChatMessage saved = chatMessageRepository.save(message);
        return ChatMessageResponseDTOMapper.convertToChatMessageResponseDTO(saved);
    }
}
