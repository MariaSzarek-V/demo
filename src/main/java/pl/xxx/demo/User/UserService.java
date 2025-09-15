package pl.xxx.demo.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Error.ResourceNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserResponseDTOMapper userMapper;
    private final UserRequestDTOMapper userCreateDTOMapper;


    public List<UserResponseDTO> getAll() {
        List <User> users = userRepository.findAll();
        return userMapper.convertToUserDTOList(users);
    }

    public UserResponseDTO get(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.convertToUserDTO(user);
    }

    public User add(UserRequestDTO userRequestDTO) {
        User user = userCreateDTOMapper.convertToUser(userRequestDTO);
        return userRepository.save(user);
    }

    public User update(Long id, UserRequestDTO userRequestDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        existingUser.setUsername(userRequestDTO.getUsername());
        existingUser.setPassword(userRequestDTO.getPassword());
        User user = userCreateDTOMapper.convertToUser(userRequestDTO);
        return userRepository.save(user);
    }

    public boolean checkIfUserUnique(UserRequestDTO dto) {

        if(userRepository.existsUsersByEmail(dto.getEmail())) {
            return false;
        }
        if (userRepository.existsUsersByUsername(dto.getUsername())) {
            return false;
        }
        return true;
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
