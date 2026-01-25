package pl.xxx.demo.ViewController;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.xxx.demo.Dashboard.DashboardService;
import pl.xxx.demo.Dashboard.DashboardStatsDTO;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserService;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardViewController {
    
    private final DashboardService dashboardService;
    private final UserService userService;
    
    @GetMapping
    public String showDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        // Pobierz zalogowanego użytkownika
        User user = userService.findByUsername(userDetails.getUsername());
        
        // Pobierz statystyki dla dashboardu
        DashboardStatsDTO stats = dashboardService.getUserDashboardStats(user);
        
        // Dodaj do modelu
        model.addAttribute("stats", stats);
        model.addAttribute("username", user.getUsername());
        
        return "dashbord"; // nazwa pliku template (dashbord.html)
    }
}
