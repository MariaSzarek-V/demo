package pl.xxx.demo.User;

import org.springframework.stereotype.Component;

@Component
public class UserRequestDTOMapper {


    public static User convertToUser(UserRequestDTO dto) {
        return User
                .builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
//                .password(passwordEncoder.encode(dto.getPassword()))
                .password(dto.getPassword())
                .build();
    }


}
