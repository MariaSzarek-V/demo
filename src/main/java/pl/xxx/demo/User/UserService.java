package pl.xxx.demo.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Error.InvalidPasswordException;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.Error.UsernameEmailAlreadyUsed;

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
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.convertToUserDTO(user);
    }

    public UserResponseDTO add(UserRequestDTO userRequestDTO) {
        if (!checkIfUserUnique(userRequestDTO)) {
            throw new UsernameEmailAlreadyUsed();
        }
        User user = userCreateDTOMapper.convertToUser(userRequestDTO);
        User saved = userRepository.save(user);
        return userMapper.convertToUserDTO(saved);
    }
//
//    public User update(Long id, UserRequestDTO userRequestDTO) {
//        User existingUser = userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//        if (checkIfUserUnique(userRequestDTO)){
//            throw new UsernameEmailAlreadyUsed();
//        }
//        existingUser.setUsername(userRequestDTO.getUsername());
//        existingUser.setPassword(userRequestDTO.getPassword());
//        User user = userCreateDTOMapper.convertToUser(userRequestDTO);
//        return userRepository.save(user);
//    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    public void updatePassword(Long id, UserPasswordUpdateDTO dto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!dto.getCurrentPassword().equals(existingUser.getPassword())) {
            throw new InvalidPasswordException();
        }
//        if (!passwordEncoder.matches(dto.getCurrentPassword(), existingUser.getPassword())) {
//            throw new InvalidPasswordException();
//        }

//        existingUser.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(existingUser);
    }

    public boolean checkIfUserUnique(UserRequestDTO dto) {
        if(userRepository.existsUsersByEmail(dto.getEmail())) return false;
        if (userRepository.existsUsersByUsername(dto.getUsername()))return false;
        return true;
    }

}
