//package pl.xxx.demo.SpaceMember;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import pl.xxx.demo.Space.Space;
//import pl.xxx.demo.User.User;
//
//
//@Entity
//@Getter
//@Setter
//public class SpaceMember {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private User user;
//    @ManyToOne
//    private User createdBy;
//}