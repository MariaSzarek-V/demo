package pl.xxx.demo.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequestDTO {
    @NotBlank(message = "Nazwa użytkownika nie może być pusta")
    @Size(min = 5, max = 50, message = "Nazwa użytkownika musi mieć od 5 do 50 znaków")
    private String username;
    @NotBlank(message = "Email nie może być pusty")
    @Email(message ="Nieprawidłowy format emaila")
    private String email;
    @NotBlank(message = "Hasło nie może być puste")
    @Size(min = 6, message = "Hasło musi mieć co najmniej 6 znaków")
    private String password;
}
