package pl.xxx.demo.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDTOMapper userMapper;
    private final UserCreateDTOMapper userCreateDTOMapper;


    public List<UserDTO> getAll() {
        List <User> users = userRepository.findAll();
        return userMapper.convertToUserDTOList(users);
    }

    public UserDTO get(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.convertToUserDTO(user);
    }

    public User add(UserCreateDTO userCreateDTO) {
        User user = userCreateDTOMapper.convertToUser(userCreateDTO);
        return userRepository.save(user);
    }
//
//    public User update(Long id, User user) {
//        User existingUser = userRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("No such user to update"));
//        existingUser.setUsername(user.getUsername());
//        existingUser.setEmail(user.getEmail());
//        existingUser.setPassword(user.getPassword());
//        return userRepository.save(existingUser);
//    }
//
//    public void delete(Long id) {
//        userRepository.deleteById(id);
//    }
//

}
