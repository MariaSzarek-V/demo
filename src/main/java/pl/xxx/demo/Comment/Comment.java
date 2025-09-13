package pl.xxx.demo.Comment;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import pl.xxx.demo.Enum.UserRole;
import pl.xxx.demo.User.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String text;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

}
