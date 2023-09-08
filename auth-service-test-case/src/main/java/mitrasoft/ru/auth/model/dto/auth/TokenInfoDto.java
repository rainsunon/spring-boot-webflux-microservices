package mitrasoft.ru.auth.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenInfoDto {

    @NotNull(message = "The userInfo is required")
    private UserInfoDto userInfo;

    private boolean isNonExpired;
}
