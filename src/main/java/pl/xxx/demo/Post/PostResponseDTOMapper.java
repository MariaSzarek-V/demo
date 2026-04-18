package pl.xxx.demo.Post;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PostResponseDTOMapper {

    public PostResponseDTO map(Post post, Integer commentsCount) {
        return PostResponseDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .gifUrl(post.getGifUrl())
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
                .build();
    }
}
