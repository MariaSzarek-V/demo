package pl.xxx.demo.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import pl.xxx.demo.Enum.UserRole;
import pl.xxx.demo.League.UserLeague;

import java.util.ArrayList;
import java.util.List;
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

    @Column(nullable = false)
    @Size(min = 6)
    private String password;

    @Builder.Default
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;

    @Column(length = 500)
    private String avatarUrl;

    @Column(length = 20)
    private String avatarColor;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<UserLeague> userLeagues = new ArrayList<>();

}
