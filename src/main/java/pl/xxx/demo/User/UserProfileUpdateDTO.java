package pl.xxx.demo.User;

import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserProfileUpdateDTO {
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    private String username;

    @Size(max = 500, message = "Avatar URL is too long")
    private String avatarUrl;
}
