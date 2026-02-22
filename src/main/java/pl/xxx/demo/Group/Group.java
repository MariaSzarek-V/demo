package pl.xxx.demo.Group;

import jakarta.persistence.*;
import lombok.*;
import pl.xxx.demo.Country.Country;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`group`")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String name;

    @OneToMany(mappedBy = "group")
    @Builder.Default
    private List<Country> countries = new ArrayList<>();
}
