package pl.xxx.demo.Post;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PostCommentResponseDTOMapper {

    public PostCommentResponseDTO map(PostComment comment) {
        PostCommentResponseDTO dto = PostCommentResponseDTO.builder()
                .id(comment.getId())
                .text(comment.getText())
                .username(comment.getUser().getUsername())
                .avatarUrl(comment.getUser().getAvatarUrl())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .reactions(comment.getReactions().stream()
                        .map(r -> PostCommentReactionDTO.builder()
                                .emoji(r.getEmoji())
                                .username(r.getUsername())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        // Map quoted comment if exists
        if (comment.getQuotedComment() != null) {
            PostComment quoted = comment.getQuotedComment();
            dto.setQuotedCommentId(quoted.getId());
            dto.setQuotedCommentText(quoted.getText());
            dto.setQuotedCommentUsername(quoted.getUser().getUsername());
        }

        return dto;
    }
}
