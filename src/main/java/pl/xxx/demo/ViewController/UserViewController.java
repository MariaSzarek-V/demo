package pl.xxx.demo.ViewController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.xxx.demo.User.UserRepository;
import pl.xxx.demo.User.UserRequestDTO;
import pl.xxx.demo.User.UserService;


@Controller
@RequiredArgsConstructor
public class UserViewController {

    private final UserService userService;
    private final UserRepository userRepository;

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
//
//    @GetMapping("/change-password")
//    public String showRegisterForm(Model model) {
//        model.addAttribute("user", new UserRequestDTO());
//        return "user/register";
//    }
//
//    @PostMapping("/change-password")
//    public String processRegisterForm(@Valid @ModelAttribute("user") UserRequestDTO user, BindingResult result) {
//
//        User currentUser = userRepository.findByUsername(user.getUsername()).get();
//
//
//        if (result.hasErrors()) {
//            return "user/register";
//        }
//        userService.add(user);
//        return "redirect:/login?success=true";
//
//    }





}
