package pl.xxx.demo.ViewController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.xxx.demo.ChatMessage.ChatMessageRequestDTO;
import pl.xxx.demo.ChatMessage.ChatMessageResponseDTO;
import pl.xxx.demo.ChatMessage.ChatMessageService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatMessageViewController {

    private final ChatMessageService chatMessageService;

    @GetMapping("/chat")
    public String showChat(Model model) {
        List<ChatMessageResponseDTO> messages = chatMessageService.getAllMessages();
        model.addAttribute("messages", messages);
        model.addAttribute("newMessage", new ChatMessageRequestDTO());
        return "chat";
    }

    @PostMapping("/chat/add")
    public String addMessage(@Valid @ModelAttribute("newMessage") ChatMessageRequestDTO dto,
                             BindingResult result,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("messages", chatMessageService.getAllMessages());
            return "chat";
        }
        chatMessageService.add(dto);
        return "redirect:/chat";
    }

}
