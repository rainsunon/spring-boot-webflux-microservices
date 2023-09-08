package mitrasoft.ru.auth.configuration.security;

import mitrasoft.ru.auth.model.dto.auth.TokenInfoDto;
import mitrasoft.ru.auth.model.dto.auth.UserInfoDto;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {
    private final JwtUtils jwtUtil;

    public AuthenticationManager(JwtUtils jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();

        TokenInfoDto tokenInfo = jwtUtil.validateToken(authToken);
        UserInfoDto userInfo = tokenInfo.getUserInfo();

        if (userInfo.getUsername() != null && tokenInfo.isNonExpired()) {
            List<SimpleGrantedAuthority> authorities = userInfo.getRoles().stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userInfo.getUsername(),
                    null,
                    authorities
            );

            return Mono.just(authenticationToken);
        } else {
            return Mono.empty();
        }
    }
}
