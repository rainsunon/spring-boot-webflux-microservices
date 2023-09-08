package mitrasoft.ru.user.model.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserInfoDto {

    @NotEmpty(message = "The first name is required")
    @Size(min = 2, max = 80, message = "The first name size must be between 2 and 80")
    private String firstName;

    @NotEmpty(message = "The last name is required")
    @Size(min = 4, max = 80, message = "The last name size must be between 4 and 80")
    private String lastName;

    @Email(message = "The email is not valid")
    @NotEmpty(message = "The email is required")
    @Size(min = 4, max = 80, message = "The first name size must be between 4 and 80")
    private String email;

    @Past(message = "The birthday must be in the past")
    @NotNull(message = "The birthday is required")
    private LocalDate birthdayAt;
}
