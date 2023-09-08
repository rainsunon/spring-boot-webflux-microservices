package mitrasoft.ru.user.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {

    @NotEmpty(message = "The username is required")
    @Size(min = 4, max = 80, message = "The username must be between 4 and 80")
    private String username;

    @NotEmpty(message = "The roles is required")
    private List<String> roles;
}
