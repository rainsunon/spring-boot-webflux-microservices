package mitrasoft.ru.auth.model.dto.auth;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserLoginDto {

    @NotEmpty(message = "The username is required")
    @Size(min = 4, max = 80, message = "The username must be between 4 and 80")
    private String username;

    @NotEmpty(message = "The password is required")
    @Size(min = 8, max = 80, message = "The password size must be between 8 and 80")
    private String password;
}
