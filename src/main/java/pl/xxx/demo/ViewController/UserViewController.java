package pl.xxx.demo.ViewController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class UserViewController {

    @GetMapping("/login")
    public String login(@RequestParam(value="error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("message", "Nieprawidłowa nazwa użytkownika lub hasło");
        }
        return "login";
    }
}
