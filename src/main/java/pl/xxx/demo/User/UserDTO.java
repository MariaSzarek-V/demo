package pl.xxx.demo.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;


    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
