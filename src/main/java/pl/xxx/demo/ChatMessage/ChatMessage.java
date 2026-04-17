package pl.xxx.demo.ChatMessage;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pl.xxx.demo.League.League;
import pl.xxx.demo.User.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chat_message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String text;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private League league;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "chat_message_reactions", joinColumns = @JoinColumn(name = "chat_message_id"))
    @Builder.Default
    private List<ChatMessageReaction> reactions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_message_id")
    private ChatMessage parentMessage;

    @ManyToOne
    @JoinColumn(name = "quoted_message_id")
    private ChatMessage quotedMessage;

}
