package pl.xxx.demo.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostReaction {
    @Column(nullable = false, length = 10)
    private String emoji;

    @Column(nullable = false, length = 50)
    private String username;
}
