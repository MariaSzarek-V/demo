package pl.xxx.demo.Post;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PostResponseDTOMapper {

    public PostResponseDTO map(Post post, Integer commentsCount) {
        if (post.isDeleted()) {
            return PostResponseDTO.builder()
                    .id(post.getId())
                    .deleted(true)
                    .deletedBy(post.getDeletedBy())
                    .username(post.getUser().getUsername())
                    .createdAt(post.getCreatedAt())
                    .commentsCount(0)
                    .build();
        }
        return PostResponseDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .username(post.getUser().getUsername())
                .avatarUrl(post.getUser().getAvatarUrl())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .reactions(post.getReactions().stream()
                        .map(r -> PostReactionDTO.builder()
                                .emoji(r.getEmoji())
                                .username(r.getUsername())
                                .build())
                        .collect(Collectors.toList()))
                .commentsCount(commentsCount)
                .deleted(false)
                .build();
    }
}
