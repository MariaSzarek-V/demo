package pl.xxx.demo.User;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserRequestDTOMapper {

    private final PasswordEncoder passwordEncoder;

    public User convertToUser(UserRequestDTO dto) {
        return User
                .builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
    }


}
