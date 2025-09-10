package pl.xxx.demo.User;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserCreateDTOMapper {

//    public static UserCreateDTO convertToUserDTO(User user) {
//        return UserCreateDTO
//                .builder()
//                .username(user.getUsername())
//                .email(user.getEmail())
//                .password(user.getPassword())
//                .build();
//    }
    public static User convertToUser(UserCreateDTO dto) {
        return User
                .builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }


}
