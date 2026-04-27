package pl.xxx.demo.Post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.xxx.demo.League.League;
import pl.xxx.demo.League.LeagueRepository;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LeagueRepository leagueRepository;
    private final PostResponseDTOMapper postMapper;

    @Transactional(readOnly = true)
    public Page<PostResponseDTO> getAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findAllOrderByCreatedAtDesc(pageable);

        return posts.map(post -> {
            Integer commentsCount = postRepository.countCommentsByPostId(post.getId());
            return postMapper.map(post, commentsCount);
        });
    }

    @Transactional(readOnly = true)
    public Page<PostResponseDTO> getPostsByLeague(int page, int size, Long leagueId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findByLeagueIdOrderByCreatedAtDesc(leagueId, pageable);

        return posts.map(post -> {
            Integer commentsCount = postRepository.countCommentsByPostId(post.getId());
            return postMapper.map(post, commentsCount);
        });
    }

    @Transactional(readOnly = true)
    public PostResponseDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post nie znaleziony"));

        Integer commentsCount = postRepository.countCommentsByPostId(post.getId());
        return postMapper.map(post, commentsCount);
    }

    @Transactional
    public PostResponseDTO createPost(PostRequestDTO dto) {
        User currentUser = getCurrentUser();
        League league = leagueRepository.findById(dto.getLeagueId())
                .orElseThrow(() -> new RuntimeException("Liga nie znaleziona"));

        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .imageUrl(dto.getImageUrl())
                .user(currentUser)
                .league(league)
                .build();

        Post savedPost = postRepository.save(post);
        return postMapper.map(savedPost, 0);
    }

    @Transactional
    public PostResponseDTO updatePost(Long id, PostRequestDTO dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post nie znaleziony"));

        User currentUser = getCurrentUser();
        if (!post.getUser().getId().equals(currentUser.getId()) && !isAdmin()) {
            throw new RuntimeException("Brak uprawnień do edycji tego posta");
        }

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setImageUrl(dto.getImageUrl());

        Post updatedPost = postRepository.save(post);
        Integer commentsCount = postRepository.countCommentsByPostId(post.getId());
        return postMapper.map(updatedPost, commentsCount);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post nie znaleziony"));

        User currentUser = getCurrentUser();
        if (!post.getUser().getId().equals(currentUser.getId()) && !isAdmin()) {
            throw new RuntimeException("Brak uprawnień do usunięcia tego posta");
        }

        post.setDeleted(true);
        post.setDeletedBy(currentUser.getUsername());
        postRepository.save(post);
    }

    @Transactional
    public PostResponseDTO addReaction(Long postId, String emoji) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post nie znaleziony"));

        String currentUsername = getCurrentUser().getUsername();

        // Check if reaction already exists
        boolean exists = post.getReactions().stream()
                .anyMatch(r -> r.getUsername().equals(currentUsername) && r.getEmoji().equals(emoji));

        if (!exists) {
            PostReaction reaction = PostReaction.builder()
                    .emoji(emoji)
                    .username(currentUsername)
                    .build();
            post.getReactions().add(reaction);
            postRepository.save(post);
        }

        Integer commentsCount = postRepository.countCommentsByPostId(post.getId());
        return postMapper.map(post, commentsCount);
    }

    @Transactional
    public PostResponseDTO removeReaction(Long postId, String emoji) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post nie znaleziony"));

        String currentUsername = getCurrentUser().getUsername();

        post.getReactions().removeIf(r ->
                r.getUsername().equals(currentUsername) && r.getEmoji().equals(emoji));

        postRepository.save(post);

        Integer commentsCount = postRepository.countCommentsByPostId(post.getId());
        return postMapper.map(post, commentsCount);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Użytkownik nie znaleziony"));
    }

    private boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }

    @Transactional(readOnly = true)
    public Long getUnreadPostsCount(Long leagueId) {
        // Get posts from last 24 hours
        LocalDateTime last24Hours = LocalDateTime.now().minusHours(24);

        if (leagueId != null) {
            return postRepository.countByLeagueIdAndCreatedAtAfter(leagueId, last24Hours);
        } else {
            return postRepository.countByCreatedAtAfter(last24Hours);
        }
    }
}
