package pl.xxx.demo.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import pl.xxx.demo.Enum.UserRole;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 5, max = 50)
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    @Size(min = 6, max = 50)
    private String password;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;

}
