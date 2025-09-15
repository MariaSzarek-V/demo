package pl.xxx.demo.ViewController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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

    /**
     * Wyświetlenie listy komentarzy i formularza dodawania nowego komentarza
     */
    @GetMapping("/comments")
    public String showComments(Model model) {
        List<CommentResponseDTO> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        model.addAttribute("newComment", new CommentRequestDTO());
        return "comments"; // nazwa pliku HTML (templates/comments.html)
    }

    /**
     * Dodawanie nowego komentarza - przypisanie do zalogowanego użytkownika
     */
    @PostMapping("/comments/add")
    public String addComment(@Valid @ModelAttribute("newComment") CommentRequestDTO dto,
                             BindingResult result,
                             Authentication authentication,
                             Model model) {

        if (result.hasErrors()) {
            // Jeśli są błędy walidacji, ponownie wyświetlamy formularz z komentarzami
            model.addAttribute("comments", commentService.getAllComments());
            return "comments";
        }

        // Pobieramy username zalogowanego użytkownika
        String loggedUsername = authentication.getName();

//         Dodajemy komentarz
        commentService.add(dto);

        // Przekierowanie, żeby odświeżyć listę komentarzy
        return "redirect:/comments";
    }
}