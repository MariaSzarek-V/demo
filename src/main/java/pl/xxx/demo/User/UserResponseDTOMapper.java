package pl.xxx.demo.User;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserResponseDTOMapper {

    public static UserResponseDTO convertToUserDTO(User user) {
        return UserResponseDTO
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .avatarColor(user.getAvatarColor())
                .userRole(user.getUserRole())
                .build();
    }

    public static List<UserResponseDTO> convertToUserDTOList(List<User> users) {
        return users
                .stream()
                .map(user -> convertToUserDTO(user))
                .toList();
    }
}
