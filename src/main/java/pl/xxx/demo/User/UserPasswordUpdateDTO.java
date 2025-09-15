package pl.xxx.demo.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserPasswordUpdateDTO {
    @NotBlank(message = "Hasło nie może być puste")
    private String currentPassword;

    @Size(min = 6)
    @NotBlank(message = "Hasło nie może być puste")
    private String newPassword;
}
