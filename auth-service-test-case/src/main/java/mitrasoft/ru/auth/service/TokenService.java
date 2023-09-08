package mitrasoft.ru.auth.service;

import mitrasoft.ru.auth.model.dto.auth.TokenInfoDto;
import mitrasoft.ru.auth.model.dto.auth.TokenPayloadDto;
import mitrasoft.ru.auth.model.dto.auth.UserLoginDto;
import mitrasoft.ru.auth.model.dto.auth.ValidateTokenDto;
import reactor.core.publisher.Mono;

public interface TokenService {
    Mono<TokenPayloadDto> generateToken(UserLoginDto userLoginDto);

    Mono<TokenInfoDto> validateToken(ValidateTokenDto validateTokenDto);
}
