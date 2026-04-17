package pl.xxx.demo.League;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;

import java.util.List;

@Tag(name = "8. League")
@RestController
@RequestMapping("/api/leagues")
@RequiredArgsConstructor
public class LeagueController {

    private final LeagueService leagueService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<LeagueResponseDTO>> getAllActiveLeagues() {
        return ResponseEntity.ok(leagueService.getAllActiveLeagues());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeagueResponseDTO> getLeagueById(@PathVariable Long id) {
        return ResponseEntity.ok(leagueService.getLeagueById(id));
    }

    @GetMapping("/my-leagues")
    public ResponseEntity<List<LeagueResponseDTO>> getMyLeagues(Authentication authentication) {
        User user = getCurrentUser(authentication);
        return ResponseEntity.ok(leagueService.getUserLeagues(user.getId()));
    }

    @PostMapping
    public ResponseEntity<LeagueResponseDTO> createLeague(
            @Valid @RequestBody LeagueCreateDTO dto,
            Authentication authentication) {
        User user = getCurrentUser(authentication);
        LeagueResponseDTO created = leagueService.createLeague(dto, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/{leagueId}/join")
    public ResponseEntity<Void> joinLeague(
            @PathVariable Long leagueId,
            Authentication authentication) {
        User user = getCurrentUser(authentication);
        leagueService.joinLeague(leagueId, user.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{leagueId}/leave")
    public ResponseEntity<Void> leaveLeague(
            @PathVariable Long leagueId,
            Authentication authentication) {
        User user = getCurrentUser(authentication);
        leagueService.leaveLeague(leagueId, user.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{leagueId}/members")
    public ResponseEntity<List<UserLeagueDTO>> getLeagueMembers(@PathVariable Long leagueId) {
        return ResponseEntity.ok(leagueService.getLeagueMembers(leagueId));
    }

    private User getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }
}
