package pl.xxx.demo.User;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="2. User")
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.get(id);
    }

    @PostMapping
    public UserRequestDTO createUser(@Valid @RequestBody UserRequestDTO user) {
        userService.add(user);
        return user;
    }
//
//    @PutMapping("/{id}")
//    public User updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO user) {
//        return userService.update(id, user);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.delete(id);
//    }
//
}
