package pl.xxx.demo.User;

import lombok.*;
import pl.xxx.demo.Enum.UserRole;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String avatarUrl;
    private UserRole userRole;
}
