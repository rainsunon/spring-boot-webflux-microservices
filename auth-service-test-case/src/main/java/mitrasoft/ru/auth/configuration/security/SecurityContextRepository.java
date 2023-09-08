package mitrasoft.ru.auth.configuration.security;

import lombok.RequiredArgsConstructor;
import mitrasoft.ru.auth.configuration.TokenProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SecurityContextRepository implements ServerSecurityContextRepository {

    private final TokenProperties tokenProperties;
    private final AuthenticationManager authenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        throw new IllegalStateException("Save method not supported!");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith(tokenProperties.getType() + " ")) {
            String authToken = authHeader.substring(7);


            return authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    authToken, authToken
                            )
                    )
                    .map(SecurityContextImpl::new);
        }
        return Mono.empty();
    }
}