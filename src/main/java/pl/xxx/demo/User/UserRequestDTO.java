package pl.xxx.demo.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequestDTO {
    @NotBlank(message = "Nazwa użytkownika nie może być pusta")
    private String username;
    @NotBlank(message = "Email nie może być pusty")
    @Email(message ="Nieprawidłowy format emaila")
    private String email;
    @NotBlank(message = "Hasło nie może być puste")
    private String password;
}
