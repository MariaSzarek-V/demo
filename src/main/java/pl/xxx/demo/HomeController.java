package pl.xxx.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // publiczny
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin"; // tylko ADMIN
    }

    @GetMapping("/index")
    public String user() {
        return "index"; // USER lub ADMIN
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}