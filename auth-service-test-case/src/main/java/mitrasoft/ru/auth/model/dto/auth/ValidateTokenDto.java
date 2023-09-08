package mitrasoft.ru.auth.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class ValidateTokenDto {

    @NotEmpty(message = "The tokenType is required")
    private final String accessToken;

    @NotEmpty(message = "The tokenType is required")
    private final String tokenType;

}
