package pl.xxx.demo.Compare;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;

@AllArgsConstructor
@RestController
@RequestMapping("/api/compare")
public class CompareController {

    private final CompareService compareService;
    private final UserRepository userRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<CompareDTO> compareWithUser(
            @PathVariable Long userId,
            @RequestParam Long leagueId,
            @AuthenticationPrincipal UserDetails userDetails) {

        User currentUser = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Current user not found"));
        CompareDTO compareDTO = compareService.compareUsers(currentUser.getId(), userId, leagueId);

        return ResponseEntity.ok(compareDTO);
    }
}
