package pl.xxx.demo.User;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateDTO {
    private String username;
    private String email;
    private String password;

}
