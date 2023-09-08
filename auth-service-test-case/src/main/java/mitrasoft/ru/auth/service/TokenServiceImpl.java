package mitrasoft.ru.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mitrasoft.ru.auth.client.UserServiceClient;
import mitrasoft.ru.auth.configuration.security.JwtUtils;
import mitrasoft.ru.auth.exception.http.BadRequestException;
import mitrasoft.ru.auth.model.dto.auth.TokenInfoDto;
import mitrasoft.ru.auth.model.dto.auth.TokenPayloadDto;
import mitrasoft.ru.auth.model.dto.auth.UserLoginDto;
import mitrasoft.ru.auth.model.dto.auth.ValidateTokenDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceClient userServiceClient;

    @Override
    public Mono<TokenPayloadDto> generateToken(UserLoginDto userLoginDto) {
        return userServiceClient.findUserByUsername(userLoginDto.getUsername())
                .flatMap(credentialsDto ->
                        passwordEncoder.matches(userLoginDto.getPassword(), credentialsDto.getHashedPassword()) ?
                                Mono.just(jwtUtils.generateToken(userLoginDto.getUsername(), credentialsDto.getRole()))
                                : Mono.error(new BadRequestException("Not correct password"))
                );
    }

    @Override
    public Mono<TokenInfoDto> validateToken(ValidateTokenDto validateTokenDto) {
        return Mono.just(
                jwtUtils.validateToken(validateTokenDto.getAccessToken())
        ).doOnError(throwable ->  {
            throwable.printStackTrace();
            throw new BadRequestException("Not valid token");
        });
    }
}
