package mitrasoft.ru.user.model.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mitrasoft.ru.user.model.enums.Role;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    @NotEmpty(message = "The first name is required")
    @Size(min = 2, max = 80, message = "The first name size must be between 2 and 80")
    private String firstName;

    @NotEmpty(message = "The last name is required")
    @Size(min = 4, max = 80, message = "The last name size must be between 4 and 80")
    private String lastName;

    @Email(message = "The email is not valid")
    @NotEmpty(message = "The email is required")
    @Size(min = 4, max = 80, message = "The email size must be between 4 and 80")
    private String email;

    @NotEmpty(message = "The password is required")
    @Size(min = 8, max = 80, message = "The password size must be between 8 and 80")
    private String password;

    @Past(message = "The birthday must be in the past")
    @NotNull(message = "The birthday is required")
    private LocalDate birthdayAt;

    @NotNull(message = "The role is required")
    private Role role;
}
