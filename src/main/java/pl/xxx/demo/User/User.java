package pl.xxx.demo.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pl.xxx.demo.Enum.UserRole;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @Size(min = 5, max = 50)
    private String username;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

}
