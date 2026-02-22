package pl.xxx.demo.Country;

import jakarta.persistence.*;
import lombok.*;
import pl.xxx.demo.Group.Group;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 10)
    private String code;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
