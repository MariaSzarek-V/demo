package pl.xxx.demo.UserPoints;

import org.springframework.web.bind.annotation.*;
import pl.xxx.demo.User.UserService;

import java.util.List;

@RestController
@RequestMapping("/points")
public class UserPointsController {

    private final UserPointsService userPointsService;
    private final UserService userService;

    public UserPointsController(UserPointsService userPointsService, UserService userService) {
        this.userPointsService = userPointsService;
        this.userService = userService;
    }

    @GetMapping
    public List<UserPoints> getAllUserPoints() {
        return userPointsService.getAll();
    }

    @GetMapping("/{id}")
    public UserPoints getUserPointsById(@PathVariable Long id) {
        return userPointsService.get(id)
                .orElseThrow(() -> new RuntimeException("UserPoints not found"));
    }

    @PostMapping
    public UserPoints createUserPoints(@RequestBody UserPoints userPoints) {
        userPointsService.add(userPoints);
        return userPoints;
    }

    @PutMapping("/{id}")
    public UserPoints updateUserPoints(@PathVariable Long id, @RequestBody UserPoints userPoints) {
        return userPointsService.update(id, userPoints);
    }

    @DeleteMapping("/{id}")
    public void deleteUserPoints(@PathVariable Long id) {
        userService.delete(id);
    }

}
