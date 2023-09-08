package mitrasoft.ru.user.configuration.security;

import lombok.RequiredArgsConstructor;
import mitrasoft.ru.user.client.AuthServiceClient;
import mitrasoft.ru.user.model.dto.auth.UserInfoDto;
import mitrasoft.ru.user.model.dto.auth.ValidateTokenDto;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final AuthServiceClient authServiceClient;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();

        return authServiceClient
                .validateToken(new ValidateTokenDto(authToken, "Bearer"))
                .flatMap(tokenInfo -> {
                    UserInfoDto userInfo = tokenInfo.getUserInfo();

                    if (userInfo.getUsername() != null && tokenInfo.isNonExpired()) {
                        return Mono.just(
                                new UsernamePasswordAuthenticationToken(
                                        userInfo.getUsername(),
                                        null,
                                        userInfo.getRoles()
                                                .stream()
                                                .map(SimpleGrantedAuthority::new)
                                                .collect(Collectors.toList())
                                )
                        );
                    } else {
                        return Mono.empty();
                    }
                });
    }
}
