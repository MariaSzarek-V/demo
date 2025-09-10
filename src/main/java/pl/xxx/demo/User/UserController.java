package pl.xxx.demo.User;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("")
//    public List<User> getAllUsers() {
//        return userService.getAll();
//    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.get(id);
    }
//
//    @PostMapping
//    public User createUser(@RequestBody User user) {
//        userService.add(user);
//        return user;
//    }
//
//    @PutMapping("/{id}")
//    public User updateUser(@PathVariable Long id, @RequestBody User user) {
//        return userService.update(id, user);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.delete(id);
//    }
//
}
