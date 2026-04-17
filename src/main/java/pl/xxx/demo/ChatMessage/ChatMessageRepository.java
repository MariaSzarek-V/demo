package pl.xxx.demo.ChatMessage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findAllByOrderByCreatedAtDesc();
    List<ChatMessage> findByLeagueIdOrderByCreatedAtDesc(Long leagueId);
}
