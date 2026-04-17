package pl.xxx.demo.League;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeagueService {

    private final LeagueRepository leagueRepository;
    private final UserLeagueRepository userLeagueRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<LeagueResponseDTO> getAllActiveLeagues() {
        return leagueRepository.findByIsActiveTrue()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LeagueResponseDTO getLeagueById(Long id) {
        League league = leagueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("League not found with id: " + id));
        return mapToResponseDTO(league);
    }

    @Transactional(readOnly = true)
    public List<LeagueResponseDTO> getUserLeagues(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        return userLeagueRepository.findByUser(user)
                .stream()
                .map(ul -> mapToResponseDTO(ul.getLeague()))
                .collect(Collectors.toList());
    }

    @Transactional
    public LeagueResponseDTO createLeague(LeagueCreateDTO dto, Long createdByUserId) {
        if (leagueRepository.existsByName(dto.getName())) {
            throw new RuntimeException("League with name '" + dto.getName() + "' already exists");
        }

        User creator = userRepository.findById(createdByUserId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + createdByUserId));

        League league = League.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .isActive(true)
                .createdBy(creator)
                .build();

        league = leagueRepository.save(league);

        // Add creator as admin
        UserLeague userLeague = UserLeague.builder()
                .user(creator)
                .league(league)
                .isAdmin(true)
                .build();
        userLeagueRepository.save(userLeague);

        return mapToResponseDTO(league);
    }

    @Transactional
    public void joinLeague(Long leagueId, Long userId) {
        League league = leagueRepository.findById(leagueId)
                .orElseThrow(() -> new RuntimeException("League not found with id: " + leagueId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        if (userLeagueRepository.existsByUserAndLeague(user, league)) {
            throw new RuntimeException("User is already a member of this league");
        }

        UserLeague userLeague = UserLeague.builder()
                .user(user)
                .league(league)
                .isAdmin(false)
                .build();
        userLeagueRepository.save(userLeague);
    }

    @Transactional
    public void leaveLeague(Long leagueId, Long userId) {
        League league = leagueRepository.findById(leagueId)
                .orElseThrow(() -> new RuntimeException("League not found with id: " + leagueId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        UserLeague userLeague = userLeagueRepository.findByUserAndLeague(user, league)
                .orElseThrow(() -> new RuntimeException("User is not a member of this league"));

        userLeagueRepository.delete(userLeague);
    }

    @Transactional(readOnly = true)
    public List<UserLeagueDTO> getLeagueMembers(Long leagueId) {
        League league = leagueRepository.findById(leagueId)
                .orElseThrow(() -> new RuntimeException("League not found with id: " + leagueId));

        return userLeagueRepository.findByLeague(league)
                .stream()
                .map(this::mapToUserLeagueDTO)
                .collect(Collectors.toList());
    }

    private LeagueResponseDTO mapToResponseDTO(League league) {
        long memberCount = userLeagueRepository.countByLeague(league);
        return LeagueResponseDTO.builder()
                .id(league.getId())
                .name(league.getName())
                .description(league.getDescription())
                .createdAt(league.getCreatedAt())
                .isActive(league.getIsActive())
                .createdByUserId(league.getCreatedBy() != null ? league.getCreatedBy().getId() : null)
                .createdByUsername(league.getCreatedBy() != null ? league.getCreatedBy().getUsername() : null)
                .memberCount(memberCount)
                .build();
    }

    private UserLeagueDTO mapToUserLeagueDTO(UserLeague userLeague) {
        return UserLeagueDTO.builder()
                .id(userLeague.getId())
                .userId(userLeague.getUser().getId())
                .username(userLeague.getUser().getUsername())
                .leagueId(userLeague.getLeague().getId())
                .leagueName(userLeague.getLeague().getName())
                .joinedAt(userLeague.getJoinedAt())
                .isAdmin(userLeague.getIsAdmin())
                .build();
    }
}
