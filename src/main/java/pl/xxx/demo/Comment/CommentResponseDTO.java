package pl.xxx.demo.Comment;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentResponseDTO {
//    private Long id;
    private String text;
    private String username;
    private LocalDateTime createdAt;
}
