package pl.xxx.demo.ViewController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.xxx.demo.Error.InvalidPasswordException;
import pl.xxx.demo.User.*;


@Controller
@RequiredArgsConstructor
public class UserViewController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserRequestDTO());
        return "user/register";
    }

    @PostMapping("/register")
    public String processRegisterForm(@Valid @ModelAttribute("user") UserRequestDTO user, BindingResult result) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            result.rejectValue("username", "error.user", "Taki użytkownik już istnieje");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            result.rejectValue("email", "error.email", "Taki email już istnieje");
        }
        if (result.hasErrors()) {
            return "user/register";
        }
        userService.add(user);
        return "redirect:/login?success=true";

    }

    @GetMapping("/change-password")
    public String showChangePasswordForm(Model model) {
        model.addAttribute("password", new UserPasswordUpdateDTO());
        return "user/change-password";
    }

    @PostMapping("/change-password")
    public String processChangePasswordForm(@Valid @ModelAttribute("password") UserPasswordUpdateDTO password, BindingResult result) {

        if (!password.getNewPassword().equals(password.getCurrentPassword())) {
            result.rejectValue("confirmPassword", "error.confirmPassword", "Hasła muszą być identyczne");
        }

        if (result.hasErrors()) {
            return "user/change-password";
        }

        try {
            userService.updatePassword(password);
        } catch (InvalidPasswordException e) {
            result.rejectValue("currentPassword", "error.currentPassword", "Stare hasło jest nieprawidłowe");
            return "user/change-password";
        }

        SecurityContextHolder.clearContext(); // wylogowanie po zmianie hasła
        return "redirect:/login?passwordChanged=true";

    }





}
