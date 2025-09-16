package pl.xxx.demo.ViewController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.xxx.demo.Comment.CommentRequestDTO;
import pl.xxx.demo.Comment.CommentResponseDTO;
import pl.xxx.demo.Comment.CommentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentViewController {

    private final CommentService commentService;

    @GetMapping("/comments")
    public String showComments(Model model) {
        List<CommentResponseDTO> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        model.addAttribute("newComment", new CommentRequestDTO());
        return "comments";
    }

    @PostMapping("/comments/add")
    public String addComment(@Valid @ModelAttribute("newComment") CommentRequestDTO dto,
                             BindingResult result,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("comments", commentService.getAllComments());
            return "comments";
        }
        commentService.add(dto);
        return "redirect:/comments";
    }

}