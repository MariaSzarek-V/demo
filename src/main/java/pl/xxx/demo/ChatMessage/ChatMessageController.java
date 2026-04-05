package pl.xxx.demo.ChatMessage;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name="7. Chat")
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @GetMapping
    public List<ChatMessageResponseDTO> getAllMessages() {
        return chatMessageService.getAllMessages();
    }

    @PostMapping
    public ChatMessageResponseDTO createMessage(@Valid @RequestBody ChatMessageRequestDTO dto) {
        return chatMessageService.add(dto);
    }

    @PostMapping("/{messageId}/reactions")
    public ChatMessageResponseDTO addReaction(
            @PathVariable Long messageId,
            @RequestParam String emoji) {
        return chatMessageService.addReaction(messageId, emoji);
    }

    @DeleteMapping("/{messageId}/reactions")
    public ChatMessageResponseDTO removeReaction(
            @PathVariable Long messageId,
            @RequestParam String emoji) {
        return chatMessageService.removeReaction(messageId, emoji);
    }
}
