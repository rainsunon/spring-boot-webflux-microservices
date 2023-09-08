package mitrasoft.ru.auth.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenPayloadDto {

    @NotEmpty(message = "The accessToken is required")
    private String accessToken;

    @NotEmpty(message = "The tokenType is required")
    private String tokenType;

    @NotNull(message = "The expiresIn is required")
    private Long expiresIn;
}
