//package pl.xxx.demo.Space;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import pl.xxx.demo.SpaceMember.SpaceMember;
//import pl.xxx.demo.User.User;
//
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//public class Space {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//    private String description;
//    @ManyToOne
//    private User createdBy;
//    @OneToMany
//    private List<SpaceMember> spacemembers;
//}
