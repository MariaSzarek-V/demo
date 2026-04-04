package pl.xxx.demo.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Error.ErrorMessages;
import pl.xxx.demo.Error.InvalidPasswordException;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.Error.UsernameEmailAlreadyUsed;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRequestDTOMapper userCreateDTOMapper;
    private final PasswordEncoder passwordEncoder;


    public List<UserResponseDTO> getAll() {
        List <User> users = userRepository.findAll();
        return UserResponseDTOMapper.convertToUserDTOList(users);
    }

    public UserResponseDTO get(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.USER_NOT_FOUND));
        return UserResponseDTOMapper.convertToUserDTO(user);
    }

    public UserResponseDTO add(UserRequestDTO userRequestDTO) {
        if (!checkIfUserUnique(userRequestDTO)) {
            throw new UsernameEmailAlreadyUsed();
        }
        User user = userCreateDTOMapper.convertToUser(userRequestDTO);
        User saved = userRepository.save(user);
        return UserResponseDTOMapper.convertToUserDTO(saved);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(ErrorMessages.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    public void updatePassword(UserPasswordUpdateDTO dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.USER_NOT_FOUND));

        if (!passwordEncoder.matches(dto.getCurrentPassword(), existingUser.getPassword())) {
            throw new InvalidPasswordException();
        }

        existingUser.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(existingUser);
    }

    public UserResponseDTO updateProfile(UserProfileUpdateDTO dto) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User existingUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.USER_NOT_FOUND));

        // Update username if provided and different
        if (dto.getUsername() != null && !dto.getUsername().equals(currentUsername)) {
            // Check if new username is already taken
            if (userRepository.existsUsersByUsername(dto.getUsername())) {
                throw new UsernameEmailAlreadyUsed();
            }
            existingUser.setUsername(dto.getUsername());
        }

        // Update avatar URL if provided
        if (dto.getAvatarUrl() != null) {
            existingUser.setAvatarUrl(dto.getAvatarUrl());
        }

        User saved = userRepository.save(existingUser);
        return UserResponseDTOMapper.convertToUserDTO(saved);
    }

    public boolean checkIfUserUnique(UserRequestDTO dto) {
        return !userRepository.existsUsersByEmail(dto.getEmail()) && !userRepository.existsUsersByUsername(dto.getUsername());
    }
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.USER_NOT_FOUND));
    }
}
