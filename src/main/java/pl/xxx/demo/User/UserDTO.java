package pl.xxx.demo.User;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {
    private String username;


    public UserDTO(String username) {
        this.username = username;
    }
}
