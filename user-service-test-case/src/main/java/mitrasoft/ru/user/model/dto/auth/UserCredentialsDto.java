package mitrasoft.ru.user.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialsDto {

    @NotEmpty(message = "The hashedPassword is required")
    String hashedPassword;

    @NotEmpty(message = "The role is required")
    String role;

}
